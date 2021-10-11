package com.example.stepcounterapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.View.inflate
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.databinding.DataBindingUtil.inflate
import com.example.stepcounterapp.callback.stepsCallback
import com.example.stepcounterapp.databinding.ActivityHomeBinding
//import com.example.stepcounterapp.databinding.ActivityHomeBinding.inflate
import com.example.stepcounterapp.databinding.ActivityHomeScreenBinding.inflate
import com.example.stepcounterapp.databinding.ActivityLoginBinding.inflate
import com.example.stepcounterapp.helper.GeneralHelper
import com.example.stepcounterapp.service.StepDetectorService
import com.google.android.material.bottomnavigation.BottomNavigationView


/*fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ViewT>(idRes)
    }
}*/


class HomeActivity : AppCompatActivity() , stepsCallback {

    //val binding = HomeActivity.inflate(layoutInflater)

    private lateinit var binding: ActivityHomeBinding
    var h = 0;
    var s = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavBar()


        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)
        //binding.TVtime.start()
        //val ctntimer = binding.TVtime
        getTimer()

        Log.w("GetAge============", LoginActivity.getAgeforCal.toString())
        Log.d("GetHeight============", LoginActivity.getHeightforCal.toString())
        Log.d("GetWeight============", LoginActivity.getWeightforCal.toString())
        Log.d("GetGender============", LoginActivity.getGenderforCal.toString())


    }
    fun getTimer(){
        val ctntimer = binding.TVtime
        ctntimer.setOnChronometerTickListener { chronometer ->
            val time = SystemClock.elapsedRealtime() - chronometer.base
            h = (time / 3600000).toInt()
            val m = (time - h * 3600000).toInt() / 60000
            s = (time - h * 3600000 - m * 60000).toInt() / 1000
            val t = (if (h < 10) "0$h" else h).toString() + ":" + (if (m < 10) "0$m" else m) + ":" + if (s < 10) "0$s" else s
            chronometer.text = t
            Log.d("Return_time=======", "$time , $s , $h ")
        }
        ctntimer.setBase(SystemClock.elapsedRealtime())
        ctntimer.setText(" 00:00:00")
        ctntimer.start()
    }

    fun getCal(): String?{

        //kmTravelled = GeneralHelper.calculateDistanceTravelledInKM().toInt()

        val age = LoginActivity.getAgeforCal
        val height = LoginActivity.getHeightforCal
        val weight = LoginActivity.getWeightforCal
        val weightInPound = weight * 2.20462262185
        val gender = LoginActivity.getGenderforCal.uppercase()
        Log.w("ReturnAge============", age.toString())
        Log.d("ReturnHeight=========", height.toString())
        Log.d("ReturnWeight=========", weightInPound.toString())
        Log.d("ReturnGender=========", gender)
        if(gender.equals("FEMALE"))
        {
            val bmr = 655 + (4.35 * weightInPound)+(4.7 * height)-(4.7 * age)
            val fcal = (bmr * ((3.3) /24 ) * s).toInt()
            Log.d("ReturnCal=========", "$fcal , $bmr , $s , $h ")
            return "$fcal Cal"

        }
        else{
            val bmr = 66 + (6.23 * weightInPound)+(12.7 * height)-(6.8 * age)
            val cal = (bmr * ((3.3) /24 ) * s).toInt()
            return "$cal Cal"
        }

    }





    override fun subscribeSteps(steps: Int) {

        binding.TVSTEPS.setText(steps.toString())
        binding.TVCALORIES.setText(GeneralHelper.getCalory(steps))
        //binding.TVCALORIES.setText(GeneralHelper.getCalories(steps))
        binding.TVDISTANCE.setText(GeneralHelper.getDistanceCovered(steps))

    }

    fun setUpNavBar(){
        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {

                    return@OnNavigationItemSelectedListener true
                }
                R.id.health -> {
                    startActivity(Intent(applicationContext, HealthActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.setting -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
             false
        })
    }
}