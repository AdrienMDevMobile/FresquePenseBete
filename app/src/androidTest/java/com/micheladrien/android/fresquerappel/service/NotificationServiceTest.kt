package com.micheladrien.android.fresquerappel.service

import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.*
import com.micheladrien.android.fresquerappel.UITestUtilitaire.checkNotification
import com.micheladrien.fresquerappel.View.views.Main_activity
import com.micheladrien.fresquerappel.View.tools.notification.NotServiceCompanion
import com.micheladrien.fresquerappel.View.tools.notification.NotificationService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

//https://developer.android.com/training/testing/ui-automator

//https://proandroiddev.com/testing-android-notifications-f147a572b257

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NotificationServiceTest {

    private val clearAllNotificationRes = "com.android.systemui:id/dismiss_text"
    private val timeout = 1000L

    private val titleNotTest = "TitleTest"
    private val textNotTest = "TextTest"

    private lateinit var mDevice : UiDevice
    private lateinit var context: Context

    private val ID_NOT_TEST = 1

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
            Main_activity::class.java
    )

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp()
    {
        //injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        context = ApplicationProvider.getApplicationContext()
    }

    /*
    //Appuyer sur le bouton clear all
    val clearAll: UiObject2 = mDevice.findObject(By.res(clearAllNotificationRes))
    clearAll.click()
    */

    //Nous verifions que la notification soit bien créée, puis nous l'affichons à la main
    @Test
    fun createAndPublishNotification() {

        mDevice.openNotification()
        mDevice.wait(Until.hasObject(By.pkg("com.android.systemui")), 10000)

        val notification = NotServiceCompanion.createTimerNotification(context , titleNotTest, textNotTest)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(ID_NOT_TEST /*+ num_notification*/, notification)
        }

        checkNotification(mDevice, titleNotTest, textNotTest)
    }

    //Nous laissons la création et l'affichage de la notification au programme.
    @Test
    fun defineNotification(){

        val intent = Intent(context, NotificationService::class.java)
        intent.putExtra(NotServiceCompanion.STRING_NOT_ID, 1)
        intent.putExtra(NotServiceCompanion.INTENT_TITLE, titleNotTest)
        intent.putExtra(NotServiceCompanion.INTENT_TEXT, textNotTest)

        val notService = NotificationService()
        notService.onReceive(context, intent)

        checkNotification(mDevice, titleNotTest, textNotTest)

    }


    //Les notifications timers sont concues pour disparaitre sans plus quand nous clickons dessus
    @Test
    fun tapTimerNotificationToDisapear(){

        mDevice.openNotification()
        mDevice.wait(Until.hasObject(By.pkg("com.android.systemui")), 10000)

        val notification = NotServiceCompanion.createTimerNotification(context , titleNotTest, textNotTest)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(ID_NOT_TEST /*+ num_notification*/, notification)
        }

        val title: UiObject2 = mDevice.findObject(By.text(titleNotTest))

        title.click()

        assertNull(mDevice.findObject(By.text(titleNotTest)))


    }


    /* La fonction va appuyer sur le bouton qui lancera le processus de notification
    private fun clickOnSendNotification() : ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Click on the send notification button"
            }

            override fun getConstraints(): Matcher<View> {
                return Matchers.allOf(isDisplayed(), isAssignableFrom(Button::class.java))
            }

            override fun perform(uiController: UiController?, view: View?) {
                view?.findViewById<View>(R.id.send_button)?.performClick()
            }
        }
    }
    */
}