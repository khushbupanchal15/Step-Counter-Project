package com.example.stepcounterapp.helper

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.stepcounterapp.HomeActivity
import com.example.stepcounterapp.LoginActivity
import com.example.stepcounterapp.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNUSED_VARIABLE")
class GeneralHelper {

    companion object{
        fun getToadyDate(): String{
            val date: Date = Calendar.getInstance().time
            val df: DateFormat = SimpleDateFormat("dd MMM yyyy")
            return df.format(date)
        }

        fun updateNotification(context: Context, service: Service, step: Int){
            val NOTIFICATION_ID = 7837
            var notiBuilder: NotificationCompat.Builder = NotificationCompat.Builder( context , "CHANNEL_ID" )
            var notiManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(context, HomeActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            var notification = NotificationCompat.Builder(context, "CHANNEL_ID")
                    .setContentTitle("Step Counter")
                    .setContentText(step.toString())
                    .setTicker(step.toString())
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setStyle(NotificationCompat.BigTextStyle().bigText("Step Counter"))
                    .setStyle(NotificationCompat.BigTextStyle().bigText(step.toString()))
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .setProgress(500, step, false)
                    //.setProgress(this.dailyStepGoal, totalSteps, false)
                    .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                    .setPriority(NotificationCompat.PRIORITY_MIN)
                    .build();


            service.startForeground(NOTIFICATION_ID, notification)
            // Set Service to run in the Foreground
            notiManager.notify(NOTIFICATION_ID, notification)

        }
        /*
        var dist = 0
        var feet = 0
        var noOfStride = 0
        var StrideLength = 0
        var DistInKm = 0
        fun calculateDistanceTravelledInKM(steps: Int): Float {
            feet = (steps * 2.5).toInt()
            noOfStride = steps/2
            StrideLength = (feet/ noOfStride)
            DistInKm = (steps * StrideLength) / 1000
            return DistInKm.toFloat()
        }*/

        const val walkingFactor = 0.57

        var CaloriesBurnedPerMile = 0.0
        var strip = 0.0
        var stepCountMile = 0.0
        var conversationFactors = 0.0
        var CaloriesBurned = 0
        //var formatter: NumberFormat = DecimalFormat("#0.00")
        var dist = 0.0

        fun getDistanceCovered(steps: Int): String? {
            val feet = (steps * 2.5).toInt()
            //dist = feet/1000
            val distance = feet/3.281
            val distanceInMiles = distance / 1609.344
            val finalDistance:Double = String.format("%.2f", distanceInMiles).toDouble()
            Log.d("finalDistance=======", finalDistance.toString())
            return " $finalDistance Mile"
        }
        fun getCalories(steps: Int): String? {
            val Cal = (steps * 0.045).toInt()
            return " $Cal Cal"
        }
        fun getCalory(steps: Int): String?
        {
            val age = LoginActivity.getAgeforCal
            val height = LoginActivity.getHeightforCal
            val weight = LoginActivity.getWeightforCal
            //val weightInPound = weight * 2.20462262185
            val gender = LoginActivity.getGenderforCal.uppercase()

            CaloriesBurnedPerMile = walkingFactor * (weight * 2.2)
            strip = height * 0.415
            stepCountMile = 160934.4 / strip
            conversationFactors = CaloriesBurnedPerMile / stepCountMile
            CaloriesBurned = (steps * conversationFactors).toInt()
            dist = (steps * strip) / 100000;

            Log.w("ReturnAge============", age.toString())
            Log.d("ReturnHeight=========", height.toString())
            Log.d("ReturnWeight=========", weight.toString())
            Log.d("ReturnGender=========", gender)
            Log.d("ReturnDist=========", dist.toString())
            Log.d("ReturnCal=========", CaloriesBurned.toString())

            return "$CaloriesBurned Cal"
        }


    }
}