package com.micheladrien.android.fresquerappel

import android.content.Context
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.micheladrien.fresquerappel.datas.RelationDirection
import com.micheladrien.fresquerappel.datas.RelationMandatory
import com.micheladrien.fresquerappel.datas.RelationModel
import com.micheladrien.fresquerappel.manager.MainDataManager
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainDataManagerTest(){
    //Regle : defini la manière dont les tests vont être menés
    //InstantTaskExecutorRule = les tests peuvent être asynchrone
    @Rule
    @JvmField
    var countingTaskExecutorRule  = CountingTaskExecutorRule()

    //Set up la variable Context
    private lateinit var context: Context
    @Before
    fun set_up(){
        context = ApplicationProvider.getApplicationContext()
    }

    //Tester la bonne définition du Singleton au moment du cast
    @Test
    fun firstDefinitionTest(){
        val mdm = MainDataManager(context)
        assertTrue(mdm.isDataInitialised())
    }

    //Tester dataManager.researchRelation() pour relation qui existe
    //Attention : Si le texte dans climat.json change : il faut adapter le test
    @Test
    fun researchRelationTest(){
        val mdm = MainDataManager(context)
        Assert.assertEquals(
            mdm.researchRelation(7, 12),
            RelationModel(
                "7",
                "12",
                RelationDirection.UPDOWN.toString(),
                RelationMandatory.MANDATORY.toString(),
                "Mettez l'une sur l'autre."
            )
        )
    }

    //Tester dataManager.researchRelation() pour une relaiton qui n'existe pas
    @Test
    fun researchFalseRelationTest(){
        val mdm = MainDataManager(context)
        Assert.assertEquals(
            mdm.researchRelation(999, 999),
            RelationModel(
                "999",
                "999",
                RelationDirection.NONE.toString(),
                RelationMandatory.OPTIONAL.toString(),
                ""
            )
        )
    }

}

