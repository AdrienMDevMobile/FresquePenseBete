package com.micheladrien.android.fresquerappel

import com.micheladrien.fresquerappel.Data.datas.CardsRelation
import com.micheladrien.fresquerappel.Data.datas.RelationDirection
import com.micheladrien.fresquerappel.Data.datas.RelationMandatory
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.Data.managers.DataProvider
import com.micheladrien.fresquerappel.Data.managers.CollageDataManagerMain
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


//@RunWith(MockitoJUnitRunner::class)
@RunWith(JUnit4::class)
class CollageDataManagerMainTest() {

    //Set up la variable Context
    //private val context: Context = ApplicationProvider.getApplicationContext()
    private val mockDataProvider: DataProvider = Mockito.mock(DataProvider::class.java)
    private lateinit var mdm : CollageDataManager

    private var climat_collage_name = "Climat"

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
        `when`(mockDataProvider.provideRelations(climat_collage_name)).thenReturn(relationList)

        mdm = CollageDataManagerMain(mockDataProvider)
    }


    @Test
    fun DMcallProvideRelations() {
        verify(mockDataProvider).provideRelations(climat_collage_name);
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

