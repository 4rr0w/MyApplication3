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
    private TextView signup;
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
        signup = (TextView)findViewById(R.id.signup);
        firebaseLogin = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);//progression bar
        FirebaseUser user = firebaseLogin.getCurrentUser();//checks if user is already logged in

        if(user != null){//if user already is logged in then skip to LoggedIn.class
            finish();
            startActivity(new Intent(MainActivity.this,Loggedin.class));
        }




            login.setOnClickListener(new View.OnClickListener() {//when user clicks on login button
            @Override
            public void onClick(View v) {
                    if(fields()) {
                        validate(username.getText().toString(), password.getText().toString());
                    }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {//if user want to register
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(MainActivity.this,Askname.class);
                startActivity(intent);

            }
        });



    }

    private void validate( String userName , String Password){//this function validate user with faribase databaese
        progress.setMessage("Authenticating");
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

    public boolean fields() {
        boolean valid = true;
        if (username.getText().toString().isEmpty() || username.length() < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError(null);
        }


        if (password.getText().toString().isEmpty() || password.length() < 4 || password.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }
        return valid;
    }

    }



