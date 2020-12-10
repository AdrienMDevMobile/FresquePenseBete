package com.micheladrien.fresquerappel.thread

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.tools.NotificationsTools

class TimerBackgroundThreadRunner(){
    private var listeTimer : ArrayList<TimerModel> = ArrayList<TimerModel>()
    private lateinit var context : Context

    fun changeListTimer(listeTimer : ArrayList<TimerModel>){
        this.listeTimer= listeTimer
    }

    fun start(startContext:Context){
        this.context = startContext
        Log.d("backGround", "2")

        //TODO Mettre tout cela en arriere plan
        var num_notification = 1

        listeTimer.forEach{
            //* 1000 pour avoir secondes au lieu de MS
            Thread.sleep(it.time_value.toLong() * 1000)
            val builder = NotificationCompat.Builder(context, "ID_NOTIFICATION")
                    .setSmallIcon(R.drawable.main_alerte)
                    .setContentTitle(it.name)
                    //.setContentText( )
                    .setPriority(NotificationCompat.PRIORITY_MAX)
            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define
                notify(NotificationsTools.NOTIFICATION_ID_TIMER + num_notification, builder.build())

                num_notification++
            }
            /*
                Log.d("onclickTimer", "1")
                timerViewModel.startTimer() */
        }
    }

    fun stop(){

    }

    //TODO Repousser la fin d'une section de X minutes
    fun repeat(numberSecond : Int){

    }
    //TODO Etape 1, cela passera par la fonction sans param (int prédéfini), + tard, mettre son paramétrage à un endroid
    fun repeat(){
        this.repeat(300)
    }
}