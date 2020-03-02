package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AskEmail extends AppCompatActivity {
    public static EditText email;//public static variables to acess these variables at in other activities. this makes our job easy but don't know is this secure?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_email);

        Button toDob = findViewById(R.id.Back);
        Button toPhone = findViewById(R.id.Next);
        email = findViewById(R.id.email);

        toDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//when back button is clicked it sets all fields to emoty and finish this activity and opens previous activity AskDOb
                email.setText("");//setting email field to empty.
                AskEmail.this.finish();//deleting this activity
                Intent intent = new Intent(AskEmail.this, AskDOB.class);
                startActivity(intent);
            }
        });

        toPhone.setOnClickListener(new View.OnClickListener() {//when next button is clicked it puts all the details entered into fields into public static valiables
                                                              //which can be acessed by another activity outside this also we don't finish this activity as we will nedd this data while registering
            @Override
            public void onClick(View v) {
                String valid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String match =email.getText().toString().trim();


                if ( !Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(match).matches() ) {
                    email.setError("Invalid email");
                } else {
                    email.setError(null);
                    Intent intent = new Intent(AskEmail.this, AskPhone.class);
                    //Intent intent = new Intent(AskEmail.this, OTP.class);//creating a new intent pointing to AskPhone
                    startActivity(intent);//starting this new intent
                }
            }
        });
    }
}
