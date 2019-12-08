package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity {
    private EditText user,email,password;
    private Button signup;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SetupUIviews();
        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload to database
                    String emailUp = email.getText().toString().trim();
                    String pwdUp = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(emailUp,pwdUp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                email.setText("");
                                password.setText("");
                                user.setText("");
                                Toast.makeText(SignUp.this, "Registration Successful!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                finish();
                                startActivity(intent);
                            }
                            else{
                                email.setText("");
                                password.setText("");
                                user.setText("");
                                Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        TextView login = (TextView)findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SetupUIviews(){
        user = (EditText)findViewById(R.id.txtName);
        email = (EditText)findViewById(R.id.txtEmail);
        password = (EditText)findViewById(R.id.txtPwd);
        signup = (Button)findViewById(R.id.btnSignup);

    }

    private Boolean validate(){
        Boolean valid = false;

        String username = user.getText().toString();
        String useremail = email.getText().toString();
        String Pass = password.getText().toString();

        if(username.isEmpty() || useremail.isEmpty() || Pass.isEmpty() )
            Toast.makeText(this,"Please Enter all the details correctly",Toast.LENGTH_SHORT).show();
        else valid = true;

        return valid;
    }
}
