package com.micheladrien.android.fresquerappel.UITest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.R
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimerFragmentTest {

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
        Main_activity::class.java
    )

    @Before
    fun set_up(){
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open())
        Espresso.onView(ViewMatchers.withId(R.id.nav_timer)).perform(ViewActions.click())
    }

    //Test : affichage des 4 timers (lot 1, 2, 3, 4+5)

    //Test : execute les timers en mode Test (se declenchent en qq secondes au lieu de qq minutes)

}