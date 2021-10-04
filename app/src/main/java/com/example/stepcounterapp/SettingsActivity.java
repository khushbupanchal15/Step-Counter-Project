package com.example.stepcounterapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // below line is to change
        // the title of our action bar.
      /*  getSupportActionBar().setTitle("Settings");

        // below line is used to check if
        // frame layout is empty or not.
        if (findViewById(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getFragmentManager().beginTransaction().attach(new SettingsFragment.Fragment());*/
            // below line is to inflate our fragment.
            //getFragmentManager().beginTransaction().add(R.id.idFrameLayout, new SettingsFragment()).commit();
       // }

       // FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        SettingsFragment settingsFragment = new SettingsFragment();
        fragmentTransaction.replace(R.id.settings,settingsFragment).commit();
    }

    public static class SettingsFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}