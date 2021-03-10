package com.micheladrien.android.fresquerappel.UITest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
    //Le fragments relation est celui qui s'ouvre en premier. Pas besoin de s'y déplacer..
    @Before
    fun setUpTaskDetailViewModel() {
        onView(withId(R.id.relation_search_button)).perform(click())
    }

    //Je vérifie que le menu pop up est bien ouvert
    @Test
    fun showRechercheDialog() {
        onView(withId(R.id.container_dialogue_fragment_recherche)).check(matches(isDisplayed()))

    }


    //Après avoir rentré 2 chiffres dans le premier editBox : passe à l'editBox2
    @Test
    fun write2FigInEditBox1(){
        onView(withId(R.id.ETcard1)).perform(typeText("01"))
        onView(withId(R.id.ETcard2)).check(matches(hasFocus()))
    }

    //Après avoir rentré 2 chiffres dans le second editBox : lance la recherche : fait disparaitre le dialogue
    @Test
    fun write2FigInEditBox1AndEditBox2(){
        onView(withId(R.id.ETcard1)).perform(typeText("01"))
        onView(withId(R.id.ETcard2)).perform(typeText("02"))
        onView(withId(R.id.container_dialogue_fragment_recherche)).check(doesNotExist())
    }

    //Lance la recherche alors qu'il manque un chiffre dans la première boite (focus sur la seconde boite)
    @Test
    fun write2FigInEditBox1AndSearch(){
        onView(withId(R.id.ETcard1)).perform(typeText("01"))
        onView(withId(R.id.BTNrecherche)).perform(click())
        onView(withId(R.id.ETcard2)).check(matches(hasFocus()))
    }

    //Lance la recherche alors qu'il manque un chiffre dans la seconde boite
    @Test
    fun write2FigInEditBox2() {
        onView(withId(R.id.ETcard2)).perform(typeText("01"))
        onView(withId(R.id.ETcard1)).check(matches(hasFocus()))
    }


    //Vérifie que la recherche affiche les numéros de la recherche + direction + texte explicatif
    @Test
    fun checkRelationShownAfterSearch(){
        onView(withId(R.id.ETcard1)).perform(typeText("07"))
        onView(withId(R.id.ETcard2)).perform(typeText("12"))


        onView(withId(R.id.txt_id_card1)).check(matches(withText("7")))
        onView(withId(R.id.txt_id_card2)).check(matches(withText("12")))
        onView(withId(R.id.text_explanation))
            .check(matches(UITestUtilitaire.hasTextFilled(R.id.text_explanation)))
    }

}