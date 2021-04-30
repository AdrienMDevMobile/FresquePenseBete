package com.micheladrien.android.fresquerappel.timerTest

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.micheladrien.android.fresquerappel.UITestUtilitaire
import com.micheladrien.android.fresquerappel.UITestUtilitaire.delayTest
import com.micheladrien.android.fresquerappel.UITestUtilitaire.textNotTest
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.tools.notification.MainTimerSExecutor
import com.micheladrien.fresquerappel.tools.notification.NotificationService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class TimerSExecutorTest {

    private lateinit var mDevice : UiDevice
    private lateinit var context: Context

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
        Main_activity::class.java
    )

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun set_up(){
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        context = ApplicationProvider.getApplicationContext()
    }

    //Verification : TimerSExecutor fait bien apparaitre une notification
    @Test
    fun TimerSExecutorTestCreateNot(){

        val time1 = TimerModel(1, textNotTest, delayTest)
        val timerArrayList = ArrayList<TimerModel>()

        timerArrayList.add(time1)

        val timerSExecutor = MainTimerSExecutor()
        timerSExecutor.executeTimers(context, timerArrayList)
        /*
        val mIntent = Intent(context, TimerService::class.java)
        mIntent.putParcelableArrayListExtra(TimerService.KEY_TIMERSERVICE_EXTRA, timerArrayList)
        TimerService.enqueueWork(context, mIntent)*/

        UITestUtilitaire.checkNotification(mDevice, context.getString(R.string.timer_notification_title), textNotTest)
        /*
        val alarmUp = PendingIntent.getBroadcast(
            context, 0,
            Intent(NotificationService::class.java),
            PendingIntent.FLAG_NO_CREATE
        ) != null

        assert(alarmUp)*/
        /*
        if (alarmUp) {
            Log.d("myTag", "Alarm is already active")
        } */
    }
}