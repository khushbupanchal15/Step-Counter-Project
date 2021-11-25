package com.example.stepcounterapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");

    EditText editname,editgender,editage,editheight,editweight,editpassword;
    Button btn_editprofile;
    String _USERNAME,_PASSWORD,_NAME,_GENDER;
    int _AGE,_HEIGHT,_WEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editname = findViewById(R.id.editName);
        editgender = findViewById(R.id.editGender);
        editage= findViewById(R.id.editAge);
        editheight= findViewById(R.id.editHeight);
        editweight= findViewById(R.id.editWeight);
       // editusername= findViewById(R.id.editUsername);
        editpassword = findViewById(R.id.editPassword);
        btn_editprofile = findViewById(R.id.btn_editprofile);
        showAllUserData();
    }
    private void showAllUserData(){
        _NAME = LoginActivity.getName;
        _AGE = LoginActivity.getAgeforCal;
        _HEIGHT = LoginActivity.getHeightforCal;
        _WEIGHT = LoginActivity.getWeightforCal;
        _GENDER = LoginActivity.getGenderforCal;
        _USERNAME = LoginActivity.getUnm;
        _PASSWORD = LoginActivity.getPwd;

        editname.setText(_NAME);
        editage.setText(String.valueOf(_AGE));
        editheight.setText(String.valueOf(_HEIGHT));
        editweight.setText(String.valueOf(_WEIGHT));
        editgender.setText(_GENDER);
        //editusername.setText(_USERNAME);
        editpassword.setText(_PASSWORD);

        Log.d("_Name===============", String.valueOf(_NAME));
        Log.d("_Username============", String.valueOf(_USERNAME));
        Log.d("_Password============", String.valueOf(_PASSWORD));
        Log.d("_Age===============", String.valueOf(_AGE));
        Log.d("_Height===============", String.valueOf(_HEIGHT));
        Log.d("_Weight===============", String.valueOf(_WEIGHT));
        Log.d("_Gender===============", String.valueOf(_GENDER));

        Log.d("eName===============", String.valueOf(editname));
        //Log.d("eUsername============", String.valueOf(editusername));
        Log.d("ePassword============", String.valueOf(editpassword));
        Log.d("eAge===============", String.valueOf(editage));
        Log.d("eHeight===============", String.valueOf(editheight));
        Log.d("eWeight===============", String.valueOf(editweight));
        Log.d("eGender===============", String.valueOf(editgender));
    }

    //btn_editprofile.setOnClickListener
    public void update(View view){
        /*if(isNameChanged() || isPasswordChanged())
        {
            Toast.makeText(this,"Data has been updated",Toast.LENGTH_LONG).show();
        }*/
        if(isNameChanged() || isPasswordChanged() || isAgeChanged() || isHeightChanged() || isWeightChanged() || isGenderChanged()){
            Toast.makeText(this,"Data has been updated",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"Data is same and can not been updated",Toast.LENGTH_LONG).show();
    }

    private boolean isGenderChanged() {
        if(!_GENDER.equals(editgender.getText().toString())){
            databaseReference.child(_USERNAME).child("gender").setValue(editgender.getText().toString());
            _GENDER = editgender.getText().toString();
            return  true;
        }
        else return false;
    }

    private boolean isAgeChanged() {
        if(_AGE != (Integer.parseInt( editage.getText().toString() ))){
            databaseReference.child(_USERNAME).child("age").setValue(Integer.parseInt( editage.getText().toString() ));
            _AGE = Integer.parseInt( editage.getText().toString() );
            return  true;
        }
        else return false;
    }
    private boolean isWeightChanged() {
        if(_WEIGHT != (Integer.parseInt( editweight.getText().toString() ))){
            databaseReference.child(_USERNAME).child("weight").setValue(Integer.parseInt( editweight.getText().toString() ));
            _WEIGHT = Integer.parseInt( editweight.getText().toString() );
            return  true;
        }
        else return false;
    }
    private boolean isHeightChanged() {
        if(_HEIGHT != (Integer.parseInt( editheight.getText().toString() ))){
            databaseReference.child(_USERNAME).child("height").setValue(Integer.parseInt( editheight.getText().toString() ));
            _HEIGHT = Integer.parseInt( editheight.getText().toString() );
            return  true;
        }
        else return false;
    }

    /*
    private boolean isUsernameChanged() {
        if(!_USERNAME.equals(editusername.getText().toString())){

            databaseReference.child(_USERNAME).setValue(editusername.getText().toString());

            databaseReference.child(_USERNAME).child("username").setValue(editusername.getText().toString());

            //databaseReference.updateChildren(_USERNAME,);
            String logunm = String.valueOf(databaseReference.getParent());
            Log.d("LogUser===========", logunm);
            _USERNAME = editusername.getText().toString();
            return  true;
        }
        else return false;
    }*/

    private boolean isPasswordChanged() {
        if(!_PASSWORD.equals(editpassword.getText().toString())){
            databaseReference.child(_USERNAME).child("password").setValue(editpassword.getText().toString());
            _PASSWORD = editpassword.getText().toString();
            return  true;
        }
        else return false;
    }

    private boolean isNameChanged() {
        if(!_NAME.equals(editname.getText().toString())){
            databaseReference.child(_USERNAME).child("name").setValue(editname.getText().toString());
            _NAME = editname.getText().toString();
            return  true;
        }
        else return false;
    }


}