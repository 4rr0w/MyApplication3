package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class AskSkills extends AppCompatActivity {
    private EditText experience;
    private EditText skills;
    private DatabaseReference userDb;
    private FirebaseUser user;
    private Chip chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_skills);

        experience = findViewById(R.id.text_exp);
        skills = findViewById(R.id.text_skills);
        Button finish = findViewById(R.id.button_finish);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userDb = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        skills.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String[] tags = skills.getText().toString().split(" ");
                //LinkedHashSet<String> hashSet = new LinkedHashSet<>(tags);

                //ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);

                LayoutInflater inflater = LayoutInflater.from(AskSkills.this);
                for(String text : tags)
                {
                    Chip chip = (Chip)inflater.inflate(R.layout.chip_item,null,false);
                    //chip.get

                }


            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDb.child("qualification").setValue(AskPicture.qual.getText().toString());
                FirebaseStorage.getInstance().getReference("Users").child(user.getUid()).child("profileImage").putFile(AskPicture.uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        userDb.child("profileImage").setValue(taskSnapshot.getStorage().getDownloadUrl().toString());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AskSkills.this,"Could Not Upload Image",Toast.LENGTH_SHORT).show();
                    }
                });
                userDb.child("experience").setValue(experience.getText().toString());
                userDb.child("skills").setValue(skills.getText().toString());

                Intent intent = new Intent(AskSkills.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
