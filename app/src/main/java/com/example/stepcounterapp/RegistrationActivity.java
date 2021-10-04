package com.example.stepcounterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://step-counter-application-f94fa-default-rtdb.firebaseio.com/");

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

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = name.getText().toString();
                String gen = gender.getText().toString();
                int uage = Integer.parseInt(age.getText().toString());
                int uheight = Integer.parseInt(height.getText().toString());
                int uweight = Integer.parseInt(weight.getText().toString());
                String usrnm = username.getText().toString();
                String pwd = password.getText().toString();

                if(uname.isEmpty() || gen.isEmpty() || uage<0 || uheight<0 || uweight<0 || usrnm.isEmpty() || pwd.isEmpty())
                {
                    Toast.makeText(RegistrationActivity.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usrnm)){
                                Toast.makeText(RegistrationActivity.this,"Username is already registered ",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("User").child(usrnm).child("name").setValue(uname);
                                databaseReference.child("User").child(usrnm).child("gender").setValue(gen);
                                databaseReference.child("User").child(usrnm).child("age").setValue(uage);
                                databaseReference.child("User").child(usrnm).child("height").setValue(uheight);
                                databaseReference.child("User").child(usrnm).child("weight").setValue(uweight);
                                databaseReference.child("User").child(usrnm).child("username").setValue(usrnm);
                                databaseReference.child("User").child(usrnm).child("password").setValue(pwd);

                                Toast.makeText(RegistrationActivity.this,"Registration completed successfully",Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(RegistrationActivity.this,LoginActivity.class);
                                startActivity(in);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });



                }
            }
        });

        //DbUser dbusr = new DbUser();

        /*
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
        });*/
    }

}