package com.micheladrien.android.fresquerappel.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.micheladrien.fresquerappel.Data.datas.CardsRelation
import com.micheladrien.fresquerappel.Data.providers.CollageDataProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CollageDataProviderJsonTest {


    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)



    //private lateinit var context : Context
    @Inject
    lateinit var jsonCollageDataProvider : CollageDataProvider
    private lateinit var list_of_relation : MutableList<CardsRelation>

    //Attention : Si le texte dans climat.json change : il faut adapter le test
    @Before
    fun setup() {
        hiltRule.inject()
        //context = ApplicationProvider.getApplicationContext()
        //jsonDataProvider = JsonDataProvider(context)
        list_of_relation = jsonCollageDataProvider.provideRelations("climat")
    }


    @Test
    fun ListCorrectTypeTest(){
        assertNotNull(list_of_relation)
    }
    @Test
    fun ListCorrectSizeTest(){
        assertTrue(list_of_relation.size > 0)
        //Il y a normalement au moins une centaine de lignes
        // Je ne met pas le chiffre précis car il peut facilement changer. A verifier dans les logs si besoin.
        assertTrue(list_of_relation.size > 100)
    }


    /*@Test
    fun initTest(){assertTrue(true)}
    //Tester dataManager.researchRelation() pour relation qui existe */
}
