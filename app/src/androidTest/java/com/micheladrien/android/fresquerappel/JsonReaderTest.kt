package com.micheladrien.android.fresquerappel
import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.RelationModel
import com.micheladrien.fresquerappel.tools.JsonReader
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class JsonReaderTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var context : Context
    private lateinit var jsonReader : JsonReader
    private lateinit var list_of_relation : MutableList<RelationModel>

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        jsonReader = JsonReader(context)
        list_of_relation = jsonReader.readJsonObject(context.getString(R.string.collage_climat))
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
        Log.d("Test_file : ListCorrectSizeTest", "list_of_relation.size = " + list_of_relation.size)
    }
}
