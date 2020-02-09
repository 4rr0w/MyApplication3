package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class cardswiper extends AppCompatActivity {
    private StorageReference mStorageRef;
    ImageView  userimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardswiper);
        userimage = (ImageView)findViewById(R.id.userIMG);
        mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://creatospace-iitd.appspot.com").child("test1DISHA.jpg");
        final File file = File.createTempFile("userimage","jpg");

        mStorageRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Bitmap user= BitmapFactory.decodeFile(file.getAbsolutePath());
                userimage.setImageBitmap(user);
            }
        });
        
    }

}
