package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.myapplication.AskEmail.email;

public class AskPhone extends AppCompatActivity {

    public static EditText phone;
    private Button toEmail,toOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_phone);


        toEmail = findViewById(R.id.Back);
        toOTP = findViewById(R.id.Next);
        phone = findViewById((R.id.phone));

        toEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//when back button is clicked it sets all fields to emoty and finish this activity and opens previous activity
                phone.setText("");//setting phone field to empty.
               // finish();//deleting this activity
                Intent intent = new Intent(AskPhone.this,AskEmail.class);
                startActivity(intent);
            }
        });

        toOTP.setOnClickListener(new View.OnClickListener() {//when next button is clicked it puts all the details entered into fields into public static valiables
            //which can be acessed by another activity outside this also we don't finish this activity as we will nedd this data while registering
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskPhone.this,OTP.class);//creating a new intent pointing to AskPhone
                startActivity(intent);//starting this new intent
            }
        });
    }
}
