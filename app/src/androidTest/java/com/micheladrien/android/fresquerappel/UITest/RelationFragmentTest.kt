package com.micheladrien.android.fresquerappel.UITest

import android.view.View
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher

//Première classe : juste pour tester l'affichage de la recherche
@RunWith(AndroidJUnit4::class)
class RelationFragmentTestOne {

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
        Main_activity::class.java
    )

    // Cette fonction vérifie que la fonction setVictoryTitle a bien été appelée
    @Test
    fun showRechercheDialog() {
        //Je click sur le bouton flottant de recherche.
        onView(withId(R.id.relation_search_button)).perform(click())
        //Je vérifie que le menu pop up est bien ouvert
        onView(withId(R.id.container_dialogue_fragment_recherche)).check(matches(isDisplayed()))

    }

}

//Seconde classe : affichagede la recherche en Before
@RunWith(AndroidJUnit4::class)
class RelationFragmentTest {

    //Regle : defini la manière dont les tests vont être menés
    //Activity test rule : dit au runner de lancer main_activity pour faire le test (doit être public)
    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
        Main_activity::class.java
    )


    //Before : set up avant de faire les tests
    @Before
    fun setUpTaskDetailViewModel() {
        onView(withId(R.id.relation_search_button)).perform(click())
    }



    //Après avoir rentré 2 chiffres dans le premier editBox : passe à l'editBox2
    @Test
    fun write2FigInEditBox1(){
        onView(withId(R.id.ETcarte1)).perform(typeText("01"))
        onView(withId(R.id.ETcarte2)).check(matches(hasFocus()))
    }

    //Après avoir rentré 2 chiffres dans le second editBox : lance la recherche : fait disparaitre le dialogue
    @Test
    fun write2FigInEditBox1AndEditBox2(){
        onView(withId(R.id.ETcarte1)).perform(typeText("01"))
        onView(withId(R.id.ETcarte2)).perform(typeText("02"))
        onView(withId(R.id.container_dialogue_fragment_recherche)).check(doesNotExist())
    }

    //Lance la recherche alors qu'il manque un chiffre dans la première boite (focus sur la seconde boite)
    @Test
    fun write2FigInEditBox1AndSearch(){
        onView(withId(R.id.ETcarte1)).perform(typeText("01"))
        onView(withId(R.id.BTNrecherche)).perform(click())
        onView(withId(R.id.ETcarte2)).check(matches(hasFocus()))
    }

    //Lance la recherche alors qu'il manque un chiffre dans la seconde boite
    @Test
    fun write2FigInEditBox2(){
        onView(withId(R.id.ETcarte2)).perform(typeText("01"))
        onView(withId(R.id.ETcarte1)).check(matches(hasFocus()))
    }

    /*
    //Vérifie que la recherche affiche les numéros de la recherche + direction + texte explicatif
    @Test
    fun checkRelationShownAfterSearch(){
        onView(withId(R.id.ETcarte1)).perform(typeText("07"))
        onView(withId(R.id.ETcarte2)).perform(typeText("12"))

        val tv1: ViewInteraction = onView(withId(R.id.textView))
        var searchText = getText(tv1).toInt()
        assert(searchText == 7)
        /**
        val textView: TextView = mainActivityTestRule.activity.findViewById(R.id.textView)
        assert(textView.text.toString().toInt() == 7)

         * val textView1: TextView = mainActivityTestRule.activity.findViewById(R.id.textView)
        assert(textView.text.toString().toInt() == 7)
         */
    }

    //https://stackoverflow.com/questions/45597008/espresso-get-text-of-element/45601564
    fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
    //https://stackoverflow.com/questions/23381459/how-to-get-text-from-textview-using-espresso

    //CHECKING VALUE OF UI FIELD ANDROID TEST

    */
}
