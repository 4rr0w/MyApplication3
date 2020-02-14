package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AskEmail extends AppCompatActivity {
    public static EditText email;//public static variables to acess these variables at in other activities. this makes our job easy but don't know is this secure?
    private Button toDob;
    private Button toPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_email);

        toDob = (Button)findViewById(R.id.Back);
        toPhone = (Button)findViewById(R.id.Next);
        email = findViewById(R.id.email);

        toDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//when back button is clicked it sets all fields to emoty and finish this activity and opens previous activity AskDOb
                email.setText("");//setting email field to empty.
                AskEmail.this.finish();//deleting this activity
                Intent intent = new Intent(AskEmail.this,AskDOB.class);
                startActivity(intent);
            }
        });

        toPhone.setOnClickListener(new View.OnClickListener() {//when next button is clicked it puts all the details entered into fields into public static valiables
                                                              //which can be acessed by another activity outside this also we don't finish this activity as we will nedd this data while registering
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    email.setError("Invalid email");

                } else {
                    email.setError(null);
                    Intent intent = new Intent(AskEmail.this, OTP.class);//creating a new intent pointing to AskPhone
                    startActivity(intent);//starting this new intent
                }
            }
        });
    }
}
