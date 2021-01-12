package com.micheladrien.android.fresquerappel

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.micheladrien.android.fresquerappel.UITest.UITestUtilitaire
import com.micheladrien.android.fresquerappel.UITest.UITestUtilitaire.checkNotification
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.tools.notification.TimerService
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep
import java.util.ArrayList

//Verification de l'execution du timer (en arrière plan, va faire une notification)
@RunWith(AndroidJUnit4::class)
class TimerTest {

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

    //TODO Verifier que nous avons une notification à la fin
    @Test
    fun createTimerNotification(){

        val time1 = TimerModel(1, textNotTest, delayTest)
        val timerArrayList = ArrayList<TimerModel>()

        timerArrayList.add(time1)

        val mIntent = Intent(context, TimerService::class.java)
        //mIntent.putExtra("maxCountValue", 1000)
        mIntent.putParcelableArrayListExtra(TimerService.KEY_TIMERSERVICE_EXTRA,timerArrayList)
        TimerService.enqueueWork(context, mIntent)

        mDevice.wait(Until.hasObject(By.text(context.getString(R.string.notification_title_timer))), 10000)

        checkNotification(mDevice, context.getString(R.string.notification_title_timer), textNotTest)

    }

    //TODO Est ce que cela appel bien la creation de notification ?
    fun onReceiveCreateNotification(){

    }

    //TODO Notification shows correct informations
    fun checkInfoOnTimerNotification(){

    }

}