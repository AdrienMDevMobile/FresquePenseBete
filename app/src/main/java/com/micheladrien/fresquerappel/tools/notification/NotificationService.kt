package com.micheladrien.fresquerappel.tools.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.micheladrien.fresquerappel.R

class NotificationService: BroadcastReceiver() {
    //C'est ici que la notification est affichée à l'utilisateur.
    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.getStringExtra(NotServiceCompanion.INTENT_TITLE)
        val text = intent.getStringExtra(NotServiceCompanion.INTENT_TEXT)
        val Notification_id_timer = intent.getIntExtra(NotServiceCompanion.STRING_NOT_ID, 0)

        val notification = NotServiceCompanion.createTimerNotification(context, title, text)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(Notification_id_timer /*+ num_notification*/, notification)

        }
    }
}
