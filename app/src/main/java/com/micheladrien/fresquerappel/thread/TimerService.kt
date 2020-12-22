package com.micheladrien.fresquerappel.thread


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.tools.NotificationsTools


class TimerService : JobIntentService() {



    companion object {
        /**
         * Unique job ID for this service.
         */
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

    /*
    fun enqueueWork(context: Context?, workerResultReceiver: ServiceResultReceiver?) {
        val intent = Intent(context, JobService::class.java)
        intent.putExtra(RECEIVER, workerResultReceiver)
        intent.action = ACTION_DOWNLOAD
        enqueueWork(context!!, JobService::class.java, DOWNLOAD_JOB_ID, intent)
    }*/
    /*
    companion object {
        var str_receiver = "com.timerService.receiver"
        const val NOTIFY_INTERVAL: Long = 1000
    }
*/
  //  private lateinit var broadcastReceiver : BroadcastReceiver

    //Code de service inspiré de de https://deepshikhapuri.wordpress.com/2016/11/07/android-countdown-timer-run-in-background/
    /*
    private val mHandler = Handler()
    var calendar: Calendar? = null
    var simpleDateFormat: SimpleDateFormat? = null
    var strDate: String? = null
    var date_current: Date? = null
    var date_diff: Date? = null
    var mpref: SharedPreferences? = null
    var mEditor: Editor? = null
    private var mTimer: Timer? = null
    var intent: Intent? = null
    */

    private var testSleep:Int = 2
    //OnBind est utilisé pour un service avec lequel on interagit. Et pour recuperer les variables passés dans l'intent

    /*
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Test Timer Background", "onStartComand")
        executeService(baseContext)
        //Return l'etat du programme
        return START_NOT_STICKY
    } */

    fun executeService(context: Context){

        //fun executeService(context: Context, listeTimer: ArrayList<TimerModel>)
        Thread.sleep(testSleep.toLong() * 1000)
        val builder = NotificationCompat.Builder(context, "ID_NOTIFICATION")
                .setSmallIcon(R.drawable.main_alerte)
                .setContentTitle("Test")
                //.setContentText( )
                .setPriority(NotificationCompat.PRIORITY_MAX)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(NotificationsTools.NOTIFICATION_ID_TIMER /*+ num_notification*/, builder.build())
        }

        /*fun executeService(context: Context, listeTimer: ArrayList<TimerModel>){
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
            }*/
            /*
                Log.d("onclickTimer", "1")
                timerViewModel.startTimer() */
        }*/
    }

    /*
    //Nous crééons notre propre TimerTask
    internal inner class TimeDisplayTimerTask : TimerTask() {
        override fun run() {
            mHandler.post {
                calendar = Calendar.getInstance()
                simpleDateFormat = SimpleDateFormat("HH:mm:ss")
                strDate = simpleDateFormat!!.format(calendar.getTime())
                Log.e("strDate", strDate)
            }
        }
    }
    */
    /*
    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    } */



    /*override fun onCreate() {
        super.onCreate()
        executeService(baseContext)
        /*
        testSleep = intent.getIntExtra("your_key_here", 0)
        Log.d("Timer test", "reception extra" + testSleep)

         */


        /*
        mpref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        mEditor = mpref.edit()
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        mTimer = Timer()
        mTimer!!.scheduleAtFixedRate(TimeDisplayTimerTask(), 5, NOTIFY_INTERVAL)
        intent = Intent(str_receiver) */
    } */
    override fun onDestroy() {
        super.onDestroy()
        //Log.e("Service finish", "Finish")
    }

    /* A s'inspirer pour faire ma fonction repeat
    private fun fn_update(str_time: String) {
        intent!!.putExtra("time", str_time)
        sendBroadcast(intent)
    } */

    //TODO Repousser la fin d'une section de X minutes
    fun repeat(numberSecond: Int){

    }
    //TODO Etape 1, cela passera par la fonction sans param (int prédéfini), + tard, mettre son paramétrage à un endroid
    fun repeat(){
        this.repeat(300)
    }


}