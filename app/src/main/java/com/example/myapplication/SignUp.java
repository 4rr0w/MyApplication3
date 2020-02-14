package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    EditText password,password2;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userDb,customerDb;
    private FirebaseUser user;
    private String dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password = findViewById(R.id.Pwd);
        password2 = findViewById(R.id.PwdRe);
        firebaseAuth = FirebaseAuth.getInstance();
        userDb = FirebaseDatabase.getInstance().getReference().child("Users");
        user = firebaseAuth.getCurrentUser();
        final DatabaseReference myDb = userDb.child(user.getUid()).child("Details");
        dob = AskDOB.day.getText().toString() + AskDOB.month.getText().toString() + AskDOB.year.getText().toString();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                if(password.getText().toString().equals(password2.getText().toString()))
                {
                    if (password.getText().toString().isEmpty() || password.length() < 6|| password.length() > 10) {
                        password.setError("between 4 and 10 alphanumeric characters");
                        valid = false;
                    } else {
                        password.setError(null);

                        Map mapDb = new HashMap<>();
                        mapDb.put("Email",AskEmail.email.getText().toString());
                        mapDb.put("FirstName",Askname.firstName.getText().toString());
                        mapDb.put("LastName",Askname.lastName.getText().toString());
                        mapDb.put("DOB",dob);
                        mapDb.put("PhoneNumber", OTP.mPhoneNumberField.getText().toString());
                        myDb.updateChildren(mapDb);

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