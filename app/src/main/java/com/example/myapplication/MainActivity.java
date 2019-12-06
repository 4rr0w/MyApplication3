package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText username;
    private EditText password;
    private TextView error;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.Userid);
        password = (EditText)findViewById(R.id.Password);
        error = (TextView) findViewById(R.id.iferror);
        login = (Button)findViewById(R.id.Login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });

        //firebase after this line
        //mAuth = FirebaseAuth.getInstance();


    }

    private void validate( String userName , String Password){
        if ((userName.equals("Admin")&& (Password.equals("login")))){
            Intent intent =  new Intent(MainActivity.this,Loggedin.class);
            startActivity(intent);

        }
        else{
            error.setText(" Wrong Username or Password! ");

        }

    }

    }

