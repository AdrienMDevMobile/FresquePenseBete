package com.micheladrien.fresquerappel.thread

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService.enqueueWork
import com.micheladrien.fresquerappel.datas.TimerModel
import java.util.*
import kotlin.collections.ArrayList

/* Options pour faire le run en arriere plan :
Pour faire tourner en arrière plan :
Utilisation des Services. Le plus simple.
ou Utilisation de RxJava. Je ne pense pas que cela soit necessaire.
RxJava a pour but de simplifier la communication des valeurs entre le thread principal et secondaire.
Est ce que une communication bilatérale est necessaire ? Je pense que non

Utilisation de timer et timertask pour gérer les temps.
 */
class TimerBackgroundThreadRunner() {
    private var listeTimer : ArrayList<TimerModel> = ArrayList<TimerModel>()
    private lateinit var context : Context

    fun changeListTimer(listeTimer: ArrayList<TimerModel>){
        this.listeTimer= listeTimer
    }

    fun start(context: Context){
        Log.d("backGround", "2")
        val mIntent = Intent(context, TimerService::class.java)
        //mIntent.putExtra("maxCountValue", 1000)
        TimerService.enqueueWork(context, mIntent)

        /*
        var t : Thread = Thread{

            fun run(){
                Log.d("Test Timer Background", "creation thread")
                context.startService(
                        Intent(context,TimerService::class.java))
            }
        }
        Log.d("backGround", "3")
        t.start()
        */

        //val intent_service = Intent(context, TimerService::class.java)
        //Log.d("Timer test", "a envoyer" + listeTimer.get(0).time_value)
        //intent_service.putExtra("your_key_here", listeTimer.get(0).time_value)
        //context.startService(intent_service)

    }

    //TODO A utiliser
    fun stop(context: Context){
        val intent_service = Intent(context, TimerService::class.java)
        context.stopService(intent_service)
    }


}