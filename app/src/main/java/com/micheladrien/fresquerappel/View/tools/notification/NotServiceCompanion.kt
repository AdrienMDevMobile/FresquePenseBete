package com.micheladrien.fresquerappel.View.tools.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.micheladrien.fresquerappel.R

//Companion values and functions for Notification
abstract class NotServiceCompanion {

    companion object {

        const val INTENT_TITLE = "INTI"
        const val INTENT_TEXT = "INTE"
        const val STRING_NOT_ID ="SNI"
        const val ID_CHANNEL_TIMER = "ID_CHANNEL_TIMER"
        //const val NOTIFICATION_ID_TIMER = 1

        fun createNotificationChannel(context: Context?) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            //Ce qui sera visible dans les parametres de l'utilisateur
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context?.getString(R.string.timer_channel_name)
                val descriptionText = context?.getString(R.string.timer_channel_description)
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(ID_CHANNEL_TIMER, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                        context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

        fun createTimerNotification(context: Context, title: String?, text:String?): Notification {
            //val intent = Intent(context, TimerService::class.java)
            //val delayTimerVal: PendingIntent = PendingIntent.getService(context, 0, intent, 0)

            val builder = NotificationCompat.Builder(context, ID_CHANNEL_TIMER)
                    .setSmallIcon(R.drawable.notification_picture)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    //Cela va automatiquement fermer la notification on click
                    .setAutoCancel(true)
                    //Cet intent ne fera rien (je veut juste que la notification parte quand l'utilisateur clique dessus)
                    .setContentIntent(PendingIntent.getActivity(context, 0, Intent(), 0))

            val toReturn = builder.build()
            //toReturn.flags = Notification.DEFAULT_LIGHTS or FLAG_AUTO_CANCEL
            return toReturn
        }
    }
}