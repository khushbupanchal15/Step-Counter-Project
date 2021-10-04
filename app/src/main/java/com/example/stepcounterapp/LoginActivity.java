package com.example.stepcounterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://step-counter-application-f94fa-default-rtdb.firebaseio.com/");

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

        // below line is used to get the instance

        /* of our Firebase database.

        firebaseDatabase = FirebaseDatabase.getInstance();



        // below line is used to get

        // reference for our database.

        databaseReference = firebaseDatabase.getReference("User");

        */

        // initializing our object class variable.

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unm = username.getText().toString();
                String pswd = pwd.getText().toString();

                if(unm.isEmpty() || pswd.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Please enter username and password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(unm)){
                                String getPassword = snapshot.child(unm).child("password").getValue(String.class);
                                //Log.e("Username--------------",unm);
                                //Log.e("Password------",getPassword);

                                if(getPassword.equals(pswd))
                                {
                                    Toast.makeText(LoginActivity.this,"Successful Logged in",Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(LoginActivity.this,HomeScreenActivity.class);
                                    startActivity(in);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,"Wrong password",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Wrong data",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this,"Data not retrieve",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    public void Register(View view) {
        Intent in = new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(in);
    }
}