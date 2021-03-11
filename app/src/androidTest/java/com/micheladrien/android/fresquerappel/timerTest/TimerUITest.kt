package com.micheladrien.android.fresquerappel.timerTest

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.micheladrien.android.fresquerappel.UITestUtilitaire.checkNotification
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.fragments.timer.TimerViewModel
import com.micheladrien.fresquerappel.managers.TimerManager
import com.micheladrien.fresquerappel.tools.notification.NotServiceCompanion
import com.micheladrien.fresquerappel.tools.notification.NotificationService
import com.micheladrien.fresquerappel.tools.notification.TimerService
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


//Verification de l'execution du timer (en arrière plan, va faire une notification)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class TimerUITest {

    private lateinit var mDevice : UiDevice
    private lateinit var context: Context

    private val textNotTest = "TextTest"
    private val delayTest = 2

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
            Main_activity::class.java
    )

    @Before
    fun set_up(){
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        context = ApplicationProvider.getApplicationContext()
    }

    //Verification : le NotificationService fait apparaitre une notification
    @Test
    fun NotificationService(){
        val intent = Intent(context, NotificationService::class.java)
        intent.putExtra(NotServiceCompanion.STRING_NOT_ID, 1)
        intent.putExtra(NotServiceCompanion.INTENT_TITLE, textNotTest)
        intent.putExtra(NotServiceCompanion.INTENT_TEXT, textNotTest)


        val pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        pendingIntent.send()

        checkNotification(mDevice, textNotTest, textNotTest)
    }

    //Verification : TimerService fait bien apparaitre une notification
    @Test
    fun TimerServiceTestCreateNot(){

        val time1 = TimerModel(1, textNotTest, delayTest)
        val timerArrayList = ArrayList<TimerModel>()

        timerArrayList.add(time1)

        val mIntent = Intent(context, TimerService::class.java)
        mIntent.putParcelableArrayListExtra(TimerService.KEY_TIMERSERVICE_EXTRA, timerArrayList)
        TimerService.enqueueWork(context, mIntent)

        checkNotification(mDevice, context.getString(R.string.timer_notification_title), textNotTest)

    }

    //La fonction VM fait bien une notification
    @UiThreadTest
    @Test
    fun VMStartTimer(){
        val vm = TimerViewModel()
        vm.populateList(TimerManager().getTestListTimer())
        vm.startTimer(context)
        //checkNotification(mDevice, context.getString(R.string.timer_notification_title), "Lot 1")
        //sleep(100)
    }


    //Le Fragment appel bien la fonction
    @Test
    fun TimerFragmentCallTimerService(){
        //Creer fragments

        //Mocking du timerService
    }

}