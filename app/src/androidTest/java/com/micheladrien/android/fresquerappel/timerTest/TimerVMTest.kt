package com.micheladrien.android.fresquerappel.timerTest

import android.content.Context
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.fragments.timer.TimerViewModel
import com.micheladrien.fresquerappel.managers.RawTimerProvider
import com.micheladrien.fresquerappel.tools.notification.TimerSExecutor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class TimerVMTest {
    /*

    private lateinit var mDevice : UiDevice
    private lateinit var context: Context

    @Inject
    lateinit var timerSExecutor: TimerSExecutor

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

    //La fonction VM fait bien une notification
    @UiThreadTest
    @Test
    fun VMStartTimer(){
        val vm = TimerViewModel(timerSExecutor, RawTimerProvider())
        vm.populateList()
        vm.startTimer(context)
        //checkNotification(mDevice, context.getString(R.string.timer_notification_title), "Lot 1")
        //sleep(100)
    }*/
}