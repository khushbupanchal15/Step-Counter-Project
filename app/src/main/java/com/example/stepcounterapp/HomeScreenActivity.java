package com.example.stepcounterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeScreenActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_report));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.ic_health));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.ic_settings));

        bottomNav.show(1,true);
        loadFragment(new HomeFragment());

        bottomNav.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()){
                    case 1: {
                        loadFragment(new HomeFragment());
                        break;
                    }
                    case 2: {
                        loadFragment(new ReportFragment());
                        break;
                    }
                    case 3: {
                        loadFragment(new HealthFragment());
                        break;
                    }
                    case 4:{
                        loadFragment(new SettingsFragment());
                        break;
                    }

                }
               // loadFragment(fragment);
                return null;



            }
        });



    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();

    }

}