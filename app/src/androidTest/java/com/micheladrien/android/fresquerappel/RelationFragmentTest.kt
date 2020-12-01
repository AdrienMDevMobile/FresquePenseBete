package com.micheladrien.android.fresquerappel


import android.R
import android.app.Application
import android.widget.EditText
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.filters.LargeTest
//import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.Mockito.mock
//import org.mockito.junit.MockitoJUnitRunner


//@RunWith(MockitoJUnitRunner::class)
class RelationFragmentTest {

    //Regle : defini la manière dont les tests vont être menés
    //InstantTaskExecutorRule = force les tests à etre synchrones
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //Before : set up avant de faire les tests
    @Before
    fun setUpTaskDetailViewModel() {

    }

    /* TEST : RECHERCHE */

    /* TEST POP UP RECHERCHE */
    //Test : affichage du pop up de recherche
    @Test
    fun setVictoryTitleSavesTitle() {

    }

    //Test : Premier editText, Apres avoir rentré deux chiffres : passe au second edit text

    //Test : Second editText, Apres avoir rentré deux chiffres : lance la recherche


}

