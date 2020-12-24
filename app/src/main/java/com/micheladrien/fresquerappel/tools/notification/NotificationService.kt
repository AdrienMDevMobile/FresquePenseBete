package com.micheladrien.fresquerappel.tools.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationManagerCompat

class NotificationService: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationsTools.createTimerNotification(context, "Titre")
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(NotificationsTools.NOTIFICATION_ID_TIMER /*+ num_notification*/, notification)

        }

        Log.d("timer test", "Alarm just fired")
    }
}
