package com.micheladrien.android.fresquerappel


import android.R
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
class MainActivityTest {
/*
  @Rule
  @JvmField
  var rule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun tappingOnTitleOpensEditDialog() {
    onView(withId(R.id.textVictoryTitle))
        .perform(click())

    onView(withId(R.id.alertTitle))
        .check(matches(isDisplayed()))

    onView(withId(R.id.button2))
        .perform(click())
  }

  @Test
  fun editingDialogUpdatesTitle() {
    onView(withId(R.id.textVictoryTitle))
        .perform(click())

    val newTitle = "Made the bed"
    onView(instanceOf(EditText::class.java))
        .perform(clearText())
        .perform(typeText(newTitle))

    onView(withText(R.string.dialog_ok))
        .perform(click())

    onView(allOf(withId(R.id.textVictoryTitle), withText(newTitle)))
        .check(matches(isDisplayed()))
  }
    */
}
