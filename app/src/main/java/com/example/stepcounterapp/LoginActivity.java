package com.example.stepcounterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView username,pwd;
    Button login,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.new_reg);
    }


    public void Register(View view) {
        Intent in = new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(in);
    }
}