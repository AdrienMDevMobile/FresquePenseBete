package com.micheladrien.fresquerappel.tools.notification


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.widget.Toast
import androidx.core.app.JobIntentService
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewModelScope
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

//Will execute the timerservice below. The TimerService is not known by other classes.
class MainTimerSExecutor @Inject constructor(): TimerSExecutor {
    companion object {
        val KEY_TIMERSERVICE_EXTRA = "KSEx"
        val JOB_ID = 2
    }



    //List of IDs of Notification called
    private val listIDNotifcationCalled = ArrayList<Int>()

    override suspend fun executeTimers(context:Context, timerArrayList : ArrayList<TimerModel>?) : Boolean{
        return prepareAllNotifications(context, timerArrayList)
    }

    override suspend fun stopAllTimers(context: Context, timerArrayList : ArrayList<TimerModel>?) : Boolean {

        val alarm = context.getSystemService(ALARM_SERVICE) as AlarmManager

        timerArrayList?.forEach(){
            val toCancel = PendingIntent.getBroadcast(
                    context, it.id,
                    Intent(context, NotificationService::class.java),
                    PendingIntent.FLAG_NO_CREATE
            )
            if(toCancel!=null){
                alarm.cancel(toCancel);//important
                toCancel.cancel();//important
            }
        }

        //https://stackoverflow.com/questions/4556670/how-to-check-if-alarmmanager-already-has-an-alarm-set
        //Pour annuler : Flag_cancel_current : https://developer.android.com/reference/android/app/PendingIntent#getBroadcast(android.content.Context,%20int,%20android.content.Intent,%20int)
        //Puis : alarmManager.cancel(pendingIntent);//important
        //pendingIntent.cancel();//important

        return true
    }

    /*
    //Il me faut rendre enqueWork publique. Me sert à set up
    private fun enqueueWork(context: Context, intent: Intent) {
        JobIntentService.enqueueWork(context, TimerService::class.java, JOB_ID, intent)
    }*/


    private fun prepareAllNotifications(context: Context, timerArrayList : ArrayList<TimerModel>?) : Boolean {
        NotServiceCompanion.createNotificationChannel(context)

        val alarmTimer = Calendar.getInstance()

        //Cette variable retient le temps des timers précédents pour décaller le suivant
        //ex : timer 1 = 10 minutes, timer 2 = 10 minutes. Timer 2 sonnera dans 20 minutes (10+10)
        var previousTimerSet = 0
        //var not_id = 0

        timerArrayList?.forEach(){
            //++not_id
            val time_wait = it.time_value*1000

            prepareOneNotification(context, alarmTimer, it.id, it.name, time_wait, previousTimerSet)

            previousTimerSet+= time_wait
        }

        return true

    }

    private fun prepareOneNotification(context: Context, alarmTimer : Calendar, notId: Int, name: String?, timeWait : Int, previousTimerSet: Int) {

        val intent = Intent(context, NotificationService::class.java)
        intent.putExtra(NotServiceCompanion.STRING_NOT_ID, notId)
        intent.putExtra(NotServiceCompanion.INTENT_TITLE, context.getString(R.string.timer_notification_title))
        intent.putExtra(NotServiceCompanion.INTENT_TEXT, name)

        val pendingIntent = PendingIntent.getBroadcast(context, notId, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val alarm = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val triggerAtMillis = alarmTimer.timeInMillis + timeWait + previousTimerSet
        alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis,  pendingIntent)



    }
}


/*

class TimerService : JobIntentService() {

    private var timerArrayList:ArrayList<TimerModel>? = null

    override fun onHandleWork(intent: Intent) {
        timerArrayList = intent.getParcelableArrayListExtra(MainTimerSExecutor.KEY_TIMERSERVICE_EXTRA)
        executeService(baseContext)
    }

    fun executeService(context: Context) {

        prepareAllNotifications(context)


    }

    private fun prepareAllNotifications(context: Context) {
        NotServiceCompanion.createNotificationChannel(context)

        val alarmTimer = Calendar.getInstance()

        //Cette variable retient le temps des timers précédents pour décaller le suivant
        //ex : timer 1 = 10 minutes, timer 2 = 10 minutes. Timer 2 sonnera dans 20 minutes (10+10)
        var previousTimerSet = 0
        var not_id = 0

        timerArrayList?.forEach(){
            ++not_id
            val time_wait = it.time_value*1000

            prepareOneNotification(context, alarmTimer, not_id, it.name, time_wait, previousTimerSet)

            previousTimerSet+= time_wait
        }

    }

    private fun prepareOneNotification(context: Context, alarmTimer : Calendar, notId: Int, name: String?, timeWait : Int, previousTimerSet: Int) {

        val intent = Intent(context, NotificationService::class.java)
        intent.putExtra(NotServiceCompanion.STRING_NOT_ID, notId)
        intent.putExtra(NotServiceCompanion.INTENT_TITLE, getString(R.string.timer_notification_title))
        intent.putExtra(NotServiceCompanion.INTENT_TEXT, name)

        val pendingIntent = PendingIntent.getBroadcast(context, notId, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val alarm = getSystemService(ALARM_SERVICE) as AlarmManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val triggerAtMillis = alarmTimer.timeInMillis + timeWait + previousTimerSet
            alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis,  pendingIntent)
        } else {
            ("VERSION.SDK_INT < M")
        }


    }


    override fun onDestroy() {
        super.onDestroy()
    }

    //Repousser la fin d'une section de X minutes
    fun repeat(numberSecond: Int){
    }
    //Etape 1, cela passera par la fonction sans param (int prédéfini), + tard, mettre son paramétrage à un endroid
    fun repeat(){
        this.repeat(300)
    }

}
 */