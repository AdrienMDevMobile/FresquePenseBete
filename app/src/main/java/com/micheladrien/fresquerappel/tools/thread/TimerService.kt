package com.micheladrien.fresquerappel.tools.thread


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.tools.notification.NotificationService
import com.micheladrien.fresquerappel.tools.notification.NotificationService.Companion.INTENT_TEXT
import com.micheladrien.fresquerappel.tools.notification.NotificationService.Companion.INTENT_TITLE
import com.micheladrien.fresquerappel.tools.notification.NotificationService.Companion.STRING_NOT_ID
import java.util.*

class TimerService : JobIntentService() {

    companion object {
        val KEY_TIMERSERVICE_EXTRA = "KSEx"
        private val JOB_ID = 2
        //Il me faut rendre enqueWork publique. Me sert à et up
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, TimerService::class.java, JOB_ID, intent)
        }

    }

    private var timerArrayList:ArrayList<TimerModel>? = null

    override fun onHandleWork(intent: Intent) {
        Log.d("Test Timer Background", "onStartComand")
        timerArrayList = intent.getParcelableArrayListExtra(KEY_TIMERSERVICE_EXTRA)
        executeService(baseContext)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Test Timer Background", "onCreate")
    }

    private var testSleep:Int = 60

    fun executeService(context: Context) {

        val alarmTimer = Calendar.getInstance()

        //Cette variable retient le temps des timers précédents pour décaller le suivant
        //ex : timer 1 = 10 minutes, timer 2 = 10 minutes. Timer 2 sonnera dans 20 minutes (10+10)
        var previousTimerSet = 0
        var not_id = 0

        timerArrayList?.forEach(){
            Log.d("testNotificationList", "Nous allons definir" + it.name)

            val intent = Intent(context, NotificationService::class.java)
            intent.putExtra(STRING_NOT_ID, ++not_id)
            intent.putExtra(INTENT_TITLE, it.id)
            intent.putExtra(INTENT_TEXT, it.name)
            Log.d("testNotificationList", "Nous avons intent" + it.name + " " + it.id  + " " + not_id)

            val pendingIntent = PendingIntent.getBroadcast(context, not_id, intent, PendingIntent.FLAG_CANCEL_CURRENT)

            val time_wait = it.time_value*1000

            val alarm = getSystemService(ALARM_SERVICE) as AlarmManager
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                val triggerAtMillis = alarmTimer.timeInMillis + time_wait + previousTimerSet
                alarm.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis,  pendingIntent)
                Log.d("testNotificationList", "nous avons set inexact avec allowWhileIdle par TimerSErvice "+ triggerAtMillis)
            } else {
                TODO("VERSION.SDK_INT < M")
            }

            previousTimerSet+= time_wait

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