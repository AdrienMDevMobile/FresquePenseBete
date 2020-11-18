package com.micheladrien.android.fresquerappel.UITest

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import com.micheladrien.fresquerappel.fragment.timer.TimerFragment
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import java.lang.Thread.sleep


//Tester slidebar : https://developer.android.com/reference/androidx/test/espresso/contrib/DrawerActions
//Il faut ajouter au gradle androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:num_version'
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
        Main_activity::class.java
    )

    @Before
    fun set_up(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
    }

    @Test
    fun testSlidebarToTimer(){
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testOpenTimeFragment(){
        onView(withId(R.id.nav_timer)).perform(click())

        //sleep(2000)

    }
    @Test
    fun testOpenSingleFragment(){
        onView(withId(R.id.nav_single)).perform(click())
        onView(withId(R.id.tv_single_not_supported)).check(matches(isDisplayed()))
        //sleep(2000)

    }
    @Test
    fun testOpenNoteFragment(){
        onView(withId(R.id.nav_notes)).perform(click())
        onView(withId(R.id.tv_note_not_supported)).check(matches(isDisplayed()))


    }
    @Test
    fun testOpenTimeThenRelationFragment(){
        onView(withId(R.id.nav_timer)).perform(click())
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_main)).perform(click())
        onView(withId(R.id.text_explication)).check(matches(isDisplayed()))
    }


}
