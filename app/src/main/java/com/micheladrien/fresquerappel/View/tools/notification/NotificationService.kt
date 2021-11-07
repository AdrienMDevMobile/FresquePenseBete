package com.micheladrien.fresquerappel.View.tools.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

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
