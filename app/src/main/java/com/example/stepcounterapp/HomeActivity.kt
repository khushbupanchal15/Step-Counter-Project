package com.example.stepcounterapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.databinding.DataBindingUtil.inflate
import com.example.stepcounterapp.callback.stepsCallback
import com.example.stepcounterapp.databinding.ActivityHomeBinding.inflate
import com.example.stepcounterapp.databinding.ActivityHomeScreenBinding.inflate
import com.example.stepcounterapp.databinding.ActivityLoginBinding.inflate
import com.example.stepcounterapp.helper.GeneralHelper
import com.example.stepcounterapp.service.StepDetectorService


fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ViewT>(idRes)
    }
}


class HomeActivity : AppCompatActivity() , stepsCallback {

    //val binding = HomeActivity.inflate(layoutInflater)

    private val stpcount: TextView by bindView(R.id.TV_STEPS)
    private val clrcount: TextView by bindView(R.id.TV_CALORIES)
    private val distcount: TextView by bindView(R.id.TV_DISTANCE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)
    }


    override fun subscribeSteps(steps: Int) {

        stpcount.setText(steps.toString())
        clrcount.setText(GeneralHelper.getCalories(steps))
        distcount.setText(GeneralHelper.getDistanceCovered(steps))
    }
}