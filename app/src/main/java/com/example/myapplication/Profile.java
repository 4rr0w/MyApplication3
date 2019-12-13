package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import android.graphics.Color;
import android.os.Bundle;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        CircularImageView circularImageView = findViewById(R.id.circularImageView);
// Set Color
        circularImageView.setCircleColor(Color.WHITE);
// or with gradient
        circularImageView.setCircleColorStart(Color.BLACK);
        circularImageView.setCircleColorEnd(Color.RED);
        circularImageView.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Set Border
        circularImageView.setBorderWidth(8f);
        circularImageView.setBorderColor(Color.BLACK);
// or with gradient
        circularImageView.setBorderColorStart(Color.BLACK);
        circularImageView.setBorderColorEnd(Color.RED);
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Add Shadow with default param
        circularImageView.setShadowEnable(true);
// or with custom param
        circularImageView.setShadowRadius(12f);
        circularImageView.setShadowColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
    }
}
