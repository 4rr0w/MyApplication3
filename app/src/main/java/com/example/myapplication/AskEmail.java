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

public class AskEmail extends AppCompatActivity {
    public static EditText email;//public static variables to acess these variables at in other activities. this makes our job easy but don't know is this secure?
    private Button toDob;
    private Button toPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_email);
        //setupUI(findViewById(R.id.parent));

        toDob = findViewById(R.id.Back);
        toPhone = findViewById(R.id.Next);
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
                if (email.getText().toString().isEmpty()) {
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

//    public static void hideSoftKeyboard(Activity activity) {
//        InputMethodManager inputMethodManager =
//                (InputMethodManager) activity.getSystemService(
//                        Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(
//                activity.getCurrentFocus().getWindowToken(), 0);
//    }
//
//    public void setupUI(View view) {
//
//        // Set up touch listener for non-text box views to hide keyboard.
//        if (!(view instanceof EditText)) {
//            view.setOnTouchListener(new View.OnTouchListener() {
//                public boolean onTouch(View v, MotionEvent event) {
//                    hideSoftKeyboard(AskEmail.this);
//                    return false;
//                }
//            });
//        }
//
//        //If a layout container, iterate over children and seed recursion.
//        if (view instanceof ViewGroup) {
//            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//                View innerView = ((ViewGroup) view).getChildAt(i);
//                setupUI(innerView);
//            }
//        }
//    }
}
