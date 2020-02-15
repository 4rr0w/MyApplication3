package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;




public class SignUp extends AppCompatActivity {
    private EditText password,password2;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userDb;
    private FirebaseUser user;
    private String dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password = findViewById(R.id.Pwd);
        password2 = findViewById(R.id.PwdRe);
        signup = findViewById(R.id.btnSignup);
        firebaseAuth = FirebaseAuth.getInstance();
        userDb = FirebaseDatabase.getInstance().getReference("Users");
        user = firebaseAuth.getCurrentUser();




        //dob = day.getText().toString() + month.getText().toString() + year.getText().toString();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(password2.getText().toString()))
                {
                    if (password.getText().toString().isEmpty() || password.length() < 6|| password.length() > 10) {
                        password.setError("between 4 and 10 alphanumeric characters");

                    } else {
                        password.setError(null);
                        String first = Askname.firstName.getText().toString();
                        String last = Askname.lastName.getText().toString();
                        String mail = AskEmail.email.getText().toString();
                        String id = userDb.push().getKey();
                        Users user = new Users(id, first,last,mail);
                        userDb.child(id).setValue(user);

                        Intent intent = new Intent(SignUp.this, Profile.class);//creating a new intent pointing to Profile
                        startActivity(intent);//starting this new intent

                    }
                }
                else{
                    password.setError("Passwords do not match.");
                    password2.setError("Passwords do not match.");
                }
            }
        });

    }
}