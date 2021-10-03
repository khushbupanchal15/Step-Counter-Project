package com.example.stepcounterapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbUser {
    private DatabaseReference databaseReference;
    public DbUser()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }
    public Task<Void> add(User usr)
    {
        return databaseReference.push().setValue(usr);
    }
}
