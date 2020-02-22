package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    StorageReference mStorageRef;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage firebaseStorage;
    TextView first,last,phone;
    private List<Users> users = new ArrayList<>();
    private Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        edit = findViewById(R.id.edit);

        final CircularImageView circularImageView = findViewById(R.id.circularImageView);
        mStorageRef = firebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Users");

        first = findViewById(R.id.Firstname);
        last = findViewById(R.id.Lastname);
        phone = findViewById(R.id.PhoneNumber);

        try {
            final File file = File.createTempFile("test1Disha", "jpg");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    users.clear();
                    List<String> keys  =  new ArrayList<>();
                    for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                        keys.add(keyNode.getKey());
                        Users user = keyNode.getValue(Users.class);
                        users.add(user);
                        first.setText(user.getFirstName());
                        last.setText(user.getLastName());
                        phone.setText(user.getPhone());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d( "loadPost:onCancelled", databaseError.toException().toString());
                }
            });


            mStorageRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap user = BitmapFactory.decodeFile(file.getAbsolutePath());
                    circularImageView.setImageBitmap(user);
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }


// Set Color
        circularImageView.setCircleColor(Color.WHITE);
// or with gradient
        circularImageView.setCircleColorStart(Color.BLACK);
        circularImageView.setCircleColorEnd(Color.RED);
        circularImageView.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Set Border
        circularImageView.setBorderWidth(3f);
        circularImageView.setBorderColor(Color.BLACK);
// or with gradient
        circularImageView.setBorderColorStart(Color.BLACK);
        circularImageView.setBorderColorEnd(Color.RED);
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Add Shadow with default param
        circularImageView.setShadowEnable(true);
// or with custom param
        circularImageView.setShadowRadius(6f);
        circularImageView.setShadowColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EditProfile.class);
                startActivity(intent);
            }
        });
    }


}
