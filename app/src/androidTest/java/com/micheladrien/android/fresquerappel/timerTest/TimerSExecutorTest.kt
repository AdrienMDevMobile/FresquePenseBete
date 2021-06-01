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
import kotlinx.coroutines.runBlocking
import org.junit.After
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
    fun TimerSExecutorCreatePendingIntent()= runBlocking {

        val time1 = TimerModel(1, textNotTest, delayTest)
        val timerArrayList = ArrayList<TimerModel>()

        timerArrayList.add(time1)

        val timerSExecutor = MainTimerSExecutor()
        timerSExecutor.executeTimers(context, timerArrayList)
        /*
        val mIntent = Intent(context, TimerService::class.java)
        mIntent.putParcelableArrayListExtra(TimerService.KEY_TIMERSERVICE_EXTRA, timerArrayList)
        TimerService.enqueueWork(context, mIntent)*/


        //https://stackoverflow.com/questions/4556670/how-to-check-if-alarmmanager-already-has-an-alarm-set
        //Pour annuler : Flag_cancel_current : https://developer.android.com/reference/android/app/PendingIntent#getBroadcast(android.content.Context,%20int,%20android.content.Intent,%20int)
        //Puis : alarmManager.cancel(pendingIntent);//important
        //pendingIntent.cancel();//important
        val alarmUp = PendingIntent.getBroadcast(
            context, 1,
            Intent(context, NotificationService::class.java),
            PendingIntent.FLAG_NO_CREATE
        ) != null

        assert(alarmUp)

        /* Not needed anymore : NotificationServiceTest checks that a notification is shown. TimerSExecut checks NotificationSErvice has been started */
    }

    //I have to change how stopAllTimers work because to get an existing pending intent I need it's requestCode.
    //We can save all sent notification request codes but that would put a lot of issues if the user exit the app.
    //So we use the list visible for the user and give it back to the Executor
    @Test
    fun TimerSExecutorCancelPendingIntent()= runBlocking {

        //We create the pendingintent and start it (like in TimerSExecutorCreatePendingIntent)
        val time1 = TimerModel(1, textNotTest, delayTest)
        val timerArrayList = ArrayList<TimerModel>()

        timerArrayList.add(time1)

        val timerSExecutor = MainTimerSExecutor()
        timerSExecutor.executeTimers(context, timerArrayList)

        //We cancel and check that is is no longer here.

        timerSExecutor.stopAllTimers(context, timerArrayList)

        val alarmDown = PendingIntent.getBroadcast(
                context, 1,
                Intent(context, NotificationService::class.java),
                PendingIntent.FLAG_NO_CREATE
        ) == null

        assert(alarmDown)

        /* Not needed anymore : NotificationServiceTest checks that a notification is shown. TimerSExecut checks NotificationSErvice has been started.
        UITestUtilitaire.checkNotification(
            mDevice,
            context.getString(R.string.timer_notification_title),
            textNotTest
        )
         */
    }

}