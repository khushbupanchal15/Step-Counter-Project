package com.example.stepcounterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class InstructionActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    ExpandableTextView expandTextView1;
    ExpandableTextView expandTextView2;
    ExpandableTextView expandTextView3;
    ExpandableTextView expandTextView4;
    ExpandableTextView expandTextView5;
    ExpandableTextView expandTextView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_activity);

        setUpNavBar();

        textView2 = findViewById(R.id.etv2).findViewById(R.id.title);
        textView2.setText("Counting when shaking phone?");
        textView2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_shake_phone_icon,0,0,0);
        textView2.setCompoundDrawablePadding(13);

        textView3 = findViewById(R.id.etv3).findViewById(R.id.title);
        textView3.setText("Accuracy");
        textView3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_accuracy,0,0,0);
        textView3.setCompoundDrawablePadding(13);

        textView4 = findViewById(R.id.etv4).findViewById(R.id.title);
        textView4.setText("Placement Suggestion");
        textView4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_placement_sugg,0,0,0);
        textView4.setCompoundDrawablePadding(13);

        textView5 = findViewById(R.id.etv5).findViewById(R.id.title);
        textView5.setText("Calories and Distance");
        textView5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_calories_distance_icon,0,0,0);
        textView5.setCompoundDrawablePadding(13);

        textView6 = findViewById(R.id.etv6).findViewById(R.id.title);
        textView6.setText("Privacy");
        textView6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_privacy,0,0,0);
        textView6.setCompoundDrawablePadding(13);

        expandTextView1 = findViewById(R.id.etv1).findViewById(R.id.expand_text_view);
        expandTextView1.setText(getString(R.string.howtouse));

        expandTextView2 = findViewById(R.id.etv2).findViewById(R.id.expand_text_view);
        expandTextView2.setText(getString(R.string.counting_when_shaking_phn));

        expandTextView3 = findViewById(R.id.etv3).findViewById(R.id.expand_text_view);
        expandTextView3.setText(getString(R.string.accuracy));

        expandTextView4 = findViewById(R.id.etv4).findViewById(R.id.expand_text_view);
        expandTextView4.setText(getString(R.string.placement_sugg));

        expandTextView5 = findViewById(R.id.etv5).findViewById(R.id.expand_text_view);
        expandTextView5.setText(getString(R.string.cal_dist));

        expandTextView6 = findViewById(R.id.etv6).findViewById(R.id.expand_text_view);
        expandTextView6.setText(getString(R.string.privacy_str));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.edit_profile:
                startActivity(new Intent(InstructionActivity.this, EditProfileActivity.class));
                break;

            case R.id.logout:
                startActivity(new Intent(InstructionActivity.this, LoginActivity.class));
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }


    private void setUpNavBar(){
        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setSelectedItemId(R.id.instructions);

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

                    case R.id.instructions:
                    {
                        return true;
                    }


                }
                return false;
            }
        });
    }
}