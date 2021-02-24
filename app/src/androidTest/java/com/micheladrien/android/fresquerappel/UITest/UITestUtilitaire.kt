package com.micheladrien.android.fresquerappel.UITest

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import junit.framework.TestCase
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

object UITestUtilitaire {

    //Inspiré par https://stackoverflow.com/questions/45597008/espresso-get-text-of-element/45601564
    //Je fais ma propre fonction matcher : vérifie que le texte est bien rempli
    fun hasTextFilled(id: Int): TypeSafeMatcher<View?> {
        return object : TypeSafeMatcher<View?>() {
            override fun describeTo(description: Description) {
                description.appendText("Has EditText/TextView $id it's string filled ?")
            }

            override fun matchesSafely(view: View?): Boolean {
                if (view !is TextView && view !is EditText) {
                    return false
                }
                val text: String = if (view is TextView) {
                    view.text.toString()
                } else {
                    (view as EditText).text.toString()
                }
                return !(text.isBlank()) && view.id == id
                //https://www.techiedelight.com/check-if-string-is-empty-or-null-kotlin/
            }

        }
    }
    //https://stackoverflow.com/questions/23381459/how-to-get-text-from-textview-using-espresso
    //https://stackoverflow.com/questions/45597008/espresso-get-text-of-element/45601564

    //Attends un peu, puis vérifie s'il existe une notification (titre, texte)
    fun checkNotification(mDevice : UiDevice, expectedTitle:String, expectedText:String){
        mDevice.wait(Until.hasObject(By.text(expectedTitle)), 10000)

        //Recupere titre, texte et le bouton d'action de la notification
        val title: UiObject2 = mDevice.findObject(By.text(expectedTitle))
        val text: UiObject2 = mDevice.findObject(By.textStartsWith(expectedText))
        //val boutonAction: UiObject2 = uiDevice.findObject(By.res("Bouton action"))

        TestCase.assertEquals(expectedTitle, title.text)
        TestCase.assertTrue(text.text.startsWith(expectedText))
        //assertEquals(expectedAllCities.toLowerCase(), allCities.text.toLowerCase())
    }
}