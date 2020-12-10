package com.micheladrien.fresquerappel.tools

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.micheladrien.fresquerappel.R

class NotificationsTools {

    companion object {

        val ID_CHANNEL_TIMER = "ID_CHANNEL_TIMER"
        val NOTIFICATION_ID_TIMER = 1

        fun createNotificationChannel(context : Context?) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            //Ce qui sera visible dans les parametres de l'utilisateur
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context?.getString(R.string.timer_channel_name)
                val descriptionText = context?.getString(R.string.timer_channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(ID_CHANNEL_TIMER, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                        context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }


}