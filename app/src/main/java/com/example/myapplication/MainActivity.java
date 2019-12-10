package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;




public class MainActivity extends AppCompatActivity {


    private EditText username;
    private EditText password;
    private TextView error;
    private Button login;
    private TextView signup;
    private FirebaseAuth firebaseLogin;
    private ProgressDialog progress;
    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;



    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;

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


        login.setOnClickListener(new View.OnClickListener() {//when user clicks on login button
            @Override
            public void onClick(View v) {
                    if(fields()) {
                        validate(username.getText().toString(), password.getText().toString());
                    }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {//if user wants to register
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(MainActivity.this,Askname.class);
                startActivity(intent);

            }
        });





        //google button sign after this





            //first we intialized the FirebaseAuth object
            firebaseLogin = FirebaseAuth.getInstance();

            //Then we need a GoogleSignInOptions object
            //And we need to build it as below
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            //Then we will get the GoogleSignInClient object from GoogleSignIn class
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            //Now we will attach a click listener to the sign_in_button
            //and inside onClick() method we are calling the signIn() method that will open
            //google sign in intent
            findViewById(R.id.google_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();

            //if the user is already signed in
            //we will close this activity
            //and take the user to profile activity
            if (firebaseLogin.getCurrentUser() != null) {
                finish();
                startActivity(new Intent(this, Loggedin.class));
            }
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            //if the requestCode is the Google Sign In code that we defined at starting
            if (requestCode == RC_SIGN_IN) {

                //Getting the GoogleSignIn Task
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    //Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);

                    //authenticating with firebase
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


            //getting the auth credential
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

            //Now using firebase we are signing in the user here
            firebaseLogin.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = firebaseLogin.getCurrentUser();
                                finish();
                                startActivity(new Intent(MainActivity.this, Loggedin.class));



                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }


        //this method is called on click
        private void signIn() {
            //getting the google signin intent
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();

            //starting the activity for result
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }






    private void validate( String userName , String Password){//this function validate user with firebase databaese
        progress.setMessage("Authenticating");
        progress.show();
       firebaseLogin.signInWithEmailAndPassword(userName,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   progress.dismiss();
                   finish();
                   startActivity(new Intent(MainActivity.this,Loggedin.class));
               }
               else{
                   progress.dismiss();
                   error.setText("Wrong Username or Password!");
                   Toast.makeText(MainActivity.this, "Login Failed!!", Toast.LENGTH_SHORT).show();

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


        if (password.getText().toString().isEmpty() || password.length() < 6|| password.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }
        return valid;
    }

}



