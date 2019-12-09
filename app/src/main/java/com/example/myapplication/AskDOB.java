package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AskDOB extends AppCompatActivity {
    private Button toMail;
    private Button toName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_dob);

        toMail = findViewById(R.id.Next);
        toName = findViewById(R.id.Back);

        toMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskDOB.this,AskEmail.class);
                startActivity(intent);
            }
        });
        toName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskDOB.this,Askname.class);
                startActivity(intent);
            }
        });




    }
}


