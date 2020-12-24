package com.micheladrien.fresquerappel.tools.thread


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.micheladrien.fresquerappel.tools.notification.NotificationService
import java.util.*

class TimerService : JobIntentService() {

    companion object {

        private val JOB_ID = 2
        //Il me faut rendre enqueWork publique. Me sert à et up
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, TimerService::class.java, JOB_ID, intent)
        }

    }

    override fun onHandleWork(intent: Intent) {
        Log.d("Test Timer Background", "onStartComand")
        executeService(baseContext)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Test Timer Background", "onCreate")
    }

    private var testSleep:Int = 60

    private var serviceRunning:Boolean = false

    fun executeService(context: Context) {

        val alarmTimer = Calendar.getInstance()

        val notification = NotificationService.createTimerNotification(context, "Titre")

        val intent = Intent(context, NotificationService::class.java)
        intent.putExtra("STRING_NOT_ID", 1)
        intent.putExtra("String_notification", notification)

        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val alarm = getSystemService(ALARM_SERVICE) as AlarmManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            alarm.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimer.timeInMillis + 3000, pendingIntent)
            Log.d("test timer", "nous avons set inexact avec allowWhileIdle par TimerSErvice")
        } else {
            TODO("VERSION.SDK_INT < M")
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        //Log.e("Service finish", "Finish")
    }

    //TODO Repousser la fin d'une section de X minutes
    fun repeat(numberSecond: Int){

        Log.d("Test timer", "fonction repeat")
    }
    //TODO Etape 1, cela passera par la fonction sans param (int prédéfini), + tard, mettre son paramétrage à un endroid
    fun repeat(){
        this.repeat(300)
    }

}