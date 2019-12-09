package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Askname extends AppCompatActivity {
    public static EditText firstName;
    public static EditText lastName;
    private Button toDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askname);

        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        toDOB = findViewById(R.id.Next);

        toDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Askname.this,AskDOB.class);
                startActivity(intent);
            }
        });


    }
}
