package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private TextView error;
    private Button login;
    private ImageView signup;
    private FirebaseAuth firebaseLogin;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText)findViewById(R.id.Userid);
        password = (EditText)findViewById(R.id.Password);
        error = (TextView) findViewById(R.id.iferror);
        login = (Button)findViewById(R.id.Login);
        signup = (ImageView)findViewById(R.id.Signup);
        firebaseLogin = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);
        FirebaseUser user = firebaseLogin.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this,Loggedin.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);

            }
        });



    }

    private void validate( String userName , String Password){
        progress.setMessage("Logging In...");
        progress.show();
       firebaseLogin.createUserWithEmailAndPassword(userName,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   progress.dismiss();
                   finish();
                   startActivity(new Intent(MainActivity.this,Loggedin.class));
               }
               else{
                   progress.dismiss();
                   Toast.makeText(MainActivity.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                   error.setText("Wrong Username or Password!");
               }

           }
       });
    }



    }



