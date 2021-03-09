package com.micheladrien.android.fresquerappel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.CardsRelation
import com.micheladrien.fresquerappel.manager.JsonDataProvider
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class JsonDataProviderTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var context : Context
    private lateinit var jsonDataProvider : JsonDataProvider
    private lateinit var list_of_relation : MutableList<CardsRelation>

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        jsonDataProvider = JsonDataProvider(context)
        list_of_relation = jsonDataProvider.provideRelations(context.getString(R.string.collage_climat))
    }

    //Tester dataManager.researchRelation() pour relation qui existe
    //Attention : Si le texte dans climat.json change : il faut adapter le test
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

}
