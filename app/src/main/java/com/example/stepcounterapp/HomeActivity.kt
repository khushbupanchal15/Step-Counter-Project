package com.example.stepcounterapp

//import com.example.stepcounterapp.databinding.ActivityHomeBinding.inflate

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.stepcounterapp.callback.stepsCallback
import com.example.stepcounterapp.databinding.ActivityHomeBinding
import com.example.stepcounterapp.helper.GeneralHelper
import com.example.stepcounterapp.service.StepDetectorService


//import kotlinx.android.synthetic.main.activity_home.*



/*
fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ViewT>(idRes)
    }
}
*/

class HomeActivity : AppCompatActivity(), stepsCallback {

    private lateinit var binding: ActivityHomeBinding
    var h = 0
    var s = 0
    //var kmTravelled = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        //TV_STEPS.setText(steps.toString())
        //TV_CALORIES.setText(GeneralHelper.getCalories(steps))
        //TV_DISTANCE.setText(GeneralHelper.getDistanceCovered(steps))
    }
}