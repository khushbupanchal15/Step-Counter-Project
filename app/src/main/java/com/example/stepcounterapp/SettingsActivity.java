package com.example.stepcounterapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        setUpNavBar();

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

    private void setUpNavBar(){
        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setSelectedItemId(R.id.setting);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                    {
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    }

                    case R.id.health:
                    {
                        startActivity(new Intent(getApplicationContext(),HealthActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    }

                    case R.id.setting:
                    {
                        return true;
                    }


                }
                return false;
            }
        });
    }
}