package com.example.stepcounterapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    public static int getAgeforCal;
    public static int  getHeightforCal;
    public static int getWeightforCal ;
    public static String getGenderforCal , getName , getUnm , getPwd ;

    /*public static int returnAge()
    {
        return getAgeforCal;
    }
    public static int returnHeight()
    {
        return getHeightforCal;
    }
    public static int returnWeight()
    {
        return getWeightforCal;
    }
    public static String returnGender()
    {
        return getGenderforCal;
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.new_reg);




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
                                    getAgeforCal = snapshot.child(unm).child("age").getValue(Integer.class);
                                    getHeightforCal = snapshot.child(unm).child("height").getValue(Integer.class);
                                    getWeightforCal = snapshot.child(unm).child("weight").getValue(Integer.class);
                                    getGenderforCal = snapshot.child(unm).child("gender").getValue(String.class);
                                    getName = snapshot.child(unm).child("name").getValue(String.class);
                                    getUnm = snapshot.child(unm).child("username").getValue(String.class);
                                    getPwd = snapshot.child(unm).child("password").getValue(String.class);

                                    Log.w("Age===============", String.valueOf(getAgeforCal));
                                    Log.d("Height===============", String.valueOf(getHeightforCal));
                                    Log.d("Weight===============", String.valueOf(getWeightforCal));
                                    Log.d("Gender===============", String.valueOf(getGenderforCal));

                                    Intent in = new Intent(LoginActivity.this,HomeActivity.class);
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