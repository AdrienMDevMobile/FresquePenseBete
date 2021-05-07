package com.micheladrien.android.fresquerappel.timerTest

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.tools.notification.MainTimerSExecutor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class TimerServiceTest {

    private lateinit var context: Context

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
        Main_activity::class.java
    )

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun set_up(){
        context = ApplicationProvider.getApplicationContext()
    }

/*
    @ExperimentalCoroutinesApi
    @Test

    fun testNotificationAlarmsAreSet()= runBlocking {

        /*
        val mIntent = Intent(context, TimerService::class.java)
        //mIntent.putExtra("maxCountValue", 1000)
        mIntent.putParcelableArrayListExtra(MainTimerSExecutor.KEY_TIMERSERVICE_EXTRA,timerArrayList)
        enqueueWork(context, mIntent)*/
    }

    private fun isAlarmSet(): Boolean {
        val intent: Intent = getTargetIntent(context)
        val service = PendingIntent.getService(
            context,
            0,
            intent,
            PendingIntent.FLAG_NO_CREATE
        )
        return service != null
    }*/
    /*
    @After
    fun tearDown() {
        if (receiver != null && receiver.getAlarmManager() != null) {
            receiver.getAlarmManager().cancel(receiver.getAlarmIntent())
        }
    }*/

    //https://guillermoorellana.es/2015/09/24/testing-android-alarmmanager.html
}