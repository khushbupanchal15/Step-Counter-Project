package com.example.stepcounterapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity1 extends AppCompatActivity implements SensorEventListener {

    /*
    private TextView stepCounter,tot_steps;
    private SensorManager sensorManager;
    private Sensor msensor;
    private boolean isCounterSensorPresent;
    int stepCount = 0;
    private  boolean walking = false;


     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stepCounter = findViewById(R.id.steps);
        tot_steps =  findViewById(R.id.tot_distance);


        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            msensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }
        else {
            Toast.makeText(getApplicationContext(),"Counter Sensor not present!!",Toast.LENGTH_SHORT);
            isCounterSensorPresent = false;
        }

         */


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*
        if(event.sensor == msensor){
            stepCount = (int) event.values[0];
            stepCounter.setText(String.valueOf(stepCount));
        }

         */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /*
    @Override
    public void onResume() {
        super.onResume();
        walking = true;
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            sensorManager.registerListener(this, msensor,SensorManager.SENSOR_DELAY_UI);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        walking = false;
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            sensorManager.unregisterListener(this, msensor);

        }
    }

     */




}