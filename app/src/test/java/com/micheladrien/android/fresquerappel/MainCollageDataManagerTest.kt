package com.micheladrien.android.fresquerappel

import com.micheladrien.fresquerappel.datas.CardsRelation
import com.micheladrien.fresquerappel.datas.RelationDirection
import com.micheladrien.fresquerappel.datas.RelationMandatory
import com.micheladrien.fresquerappel.manager.CollageDataManager
import com.micheladrien.fresquerappel.manager.DataProvider
import com.micheladrien.fresquerappel.manager.MainCollageDataManager
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainCollageDataManagerTest() {

    //Set up la variable Context
    //private val context: Context = ApplicationProvider.getApplicationContext()
    private val mockDataProvider: DataProvider = Mockito.mock(DataProvider::class.java)
    private lateinit var mdm : CollageDataManager

    @Before
    fun set_up() {
        //context = ApplicationProvider.getApplicationContext()
        val relationList = mutableListOf<CardsRelation>()
        relationList.add(
            CardsRelation(
                "7",
                "12",
                RelationDirection.UPDOWN.toString(),
                RelationMandatory.MANDATORY.toString(),
                "Mettez l'une sur l'autre."
            )
        )
        `when`(mockDataProvider.provideRelations("climat")).thenReturn(relationList)

        mdm = MainCollageDataManager(mockDataProvider)
    }

    //Tester la bonne définition du Singleton au moment du cast
    @Test
    fun DMfirstDefinitionTest() {
        //assertTrue(true)
        assertTrue(mdm.isDataInitialised())
    }

    @Test
    fun DMcallProvideRelations() {
        verify(mockDataProvider).provideRelations("climat");
    }

    @Test
    fun DMchangeCollage() {
        mdm.changeCollage("other")
        verify(mockDataProvider).provideRelations("other");

    }

    //Tester dataManager.researchRelation() pour relation qui existe
    //Attention : Si le texte dans climat.json change : il faut adapter le test
    @Test
    fun DMresearchRelationTest(){
        Assert.assertEquals(
            mdm.researchRelation(7, 12),
            CardsRelation(
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
    fun DMresearchFalseRelationTest(){
        Assert.assertEquals(
            mdm.researchRelation(999, 999),
                CardsRelation(
                "999",
                "999",
                RelationDirection.NONE.toString(),
                RelationMandatory.OPTIONAL.toString(),
                ""
            )
        )
    }

}

