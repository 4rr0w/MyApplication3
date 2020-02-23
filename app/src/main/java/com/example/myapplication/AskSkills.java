package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class AskSkills extends AppCompatActivity {
    private EditText experience;
    private EditText skills;
    private Button finish;
    private DatabaseReference userDb;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_skills);

        experience = findViewById(R.id.text_exp);
        skills = findViewById(R.id.text_skills);
        finish = findViewById(R.id.button_finish);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userDb = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDb.child("qualification").setValue(AskPicture.qual.getText().toString());
                FirebaseStorage.getInstance().getReference("Users").child(user.getUid()).child("profileImage").putFile(AskPicture.uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        userDb.child("profileImage").setValue(taskSnapshot.getStorage().getDownloadUrl().toString());
                    }
                });
                userDb.child("experience").setValue(experience.getText().toString());
                userDb.child("skills").setValue(skills.getText().toString());

                Intent intent = new Intent(AskSkills.this,Profile.class);
                startActivity(intent);
            }
        });
    }
}
