package com.micheladrien.android.fresquerappel.view

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.micheladrien.fresquerappel.View.view.Main_activity
import com.micheladrien.fresquerappel.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.DrawerActions
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before


//Tester slidebar : https://developer.android.com/reference/androidx/test/espresso/contrib/DrawerActions
//Il faut ajouter au gradle androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:num_version'
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun set_up(){
        val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
                Main_activity::class.java
        )
        mainActivityTestRule.launchActivity(Intent())


        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
    }

    @Test
    fun testSlidebarToTimer(){
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    }


    @Test
    fun testOpenAboutFragment(){
        onView(withId(R.id.nav_about)).perform(click())
        onView(withId(R.id.tv_about)).check(matches(isDisplayed()))


    }
    @Test
    fun testOpenTimeThenRelationFragment(){
        onView(withId(R.id.nav_timer)).perform(click())
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_relation)).perform(click())
        onView(withId(R.id.text_explanation)).check(matches(isDisplayed()))
    }


}
