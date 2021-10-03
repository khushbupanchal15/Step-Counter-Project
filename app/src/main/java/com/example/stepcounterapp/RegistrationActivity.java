package com.example.stepcounterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        EditText name = findViewById(R.id.Name);
        EditText gender = findViewById(R.id.Gender);
        EditText age= findViewById(R.id.Age);
        EditText height= findViewById(R.id.Height);
        EditText weight= findViewById(R.id.Weight);
        EditText username= findViewById(R.id.Username);
        EditText password = findViewById(R.id.Password);
        Button btn_reg = findViewById(R.id.btn_reg);

        DbUser dbusr = new DbUser();

        btn_reg.setOnClickListener(v->
        {
            User usr = new User(name.getText().toString(),gender.getText().toString(),Integer.parseInt(age.getText().toString()),Integer.parseInt(height.getText().toString()),Integer.parseInt(weight.getText().toString()),username.getText().toString(),password.getText().toString());

            dbusr.add(usr).addOnSuccessListener(suc->
            {
                Toast.makeText(this,"Registration completed successfully",Toast.LENGTH_SHORT).show();
                Intent in = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(in);
            }).addOnFailureListener(er->
            {
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });
    }

}