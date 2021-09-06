package com.example.stepcounterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    ImageView logo,image;
    TextView appname;
    Button btnnext;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        appname = findViewById(R.id.appname);
        image = findViewById(R.id.image);
        lottieAnimationView = findViewById(R.id.lottie);
        btnnext = findViewById(R.id.btnnext);



        image.animate().translationY(-1600).setDuration(1000).setStartDelay(3000);
        btnnext.animate().translationY(-1600).setDuration(1000).setStartDelay(3000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(3000);
        appname.animate().translationY(1400).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(3000);

    }


    public void Login(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}

