package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText password, password2;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userDb;
    private FirebaseUser user;
    private String dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        findViewById(R.id.btnSignup).setOnClickListener(this);


    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(SignUp.this, Profile.class);//creating a new intent pointing to Profile
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);//starting this new intent

        } else {
            Log.w("SignUp", "createUserWithEmail:failure");
            Toast.makeText(SignUp.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        password = findViewById(R.id.Pwd);
        password2 = findViewById(R.id.PwdRe);
        signup = findViewById(R.id.btnSignup);
        firebaseAuth = FirebaseAuth.getInstance();
        userDb = FirebaseDatabase.getInstance().getReference("Users");
        user = firebaseAuth.getCurrentUser();

        if (password.getText().toString().equals(password2.getText().toString())) {
            if (password.getText().toString().isEmpty() || password.length() < 6 || password.length() > 10) {
                password.setError("between 6 and 10 alphanumeric characters");

            } else {
                password.setError(null);
                String link = "";
                String phoneNumber = AskPhone.phone.getText().toString();
                String pass = password.getText().toString();
                String first = Askname.firstName.getText().toString();
                String last = Askname.lastName.getText().toString();
                String mail = AskEmail.email.getText().toString();
                //String id = userDb.push().getKey();
                Users user1 = new Users(user.getUid(), first, last, mail, link, phoneNumber, null, null);
                userDb.child(user.getUid()).setValue(user1);

                firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SignUp", "createUserWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    updateUI(user);


                                } else {

                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getApplicationContext(), "You are already registered!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignUp.this, task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    // If sign in fails, display a message to the user.
                                    Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                                    updateUI(null);
                                }

                                // ...
                            }
                        });


            }
        } else {
            password.setError("Passwords do not match.");
            password2.setError("Passwords do not match.");
        }
    }

}