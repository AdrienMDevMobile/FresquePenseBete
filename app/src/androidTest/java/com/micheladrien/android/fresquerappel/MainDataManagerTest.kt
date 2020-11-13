package com.micheladrien.android.fresquerappel

import com.micheladrien.fresquerappel.datas.Relation
import com.micheladrien.fresquerappel.datas.RelationDirection
import com.micheladrien.fresquerappel.datas.RelationMandatory
import com.micheladrien.fresquerappel.datas.RelationModel
import com.micheladrien.fresquerappel.manager.MainDataManager
import com.micheladrien.fresquerappel.tools.JsonReader
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SingletonDataManagerTest {

    //Le JsonReader qui va lire dans les fichiers est une maquette
    @Mock
    val mockJsonReader: JsonReader = mock(JsonReader::class.java)

    @Before
    fun set_up() {
        //Line that doesn't work
        val listOfJsonReader = mutableListOf<RelationModel>()
        listOfJsonReader.add(RelationModel("1","2", RelationDirection.DOWN.toString(), RelationMandatory.MANDATORY.toString(), "Test text"))
        `when`(mockJsonReader.readJsonObject("test")).thenReturn(listOfJsonReader)
    }

    //Nous verifions qu'à la base, les données ne sont pas marquées comme chargées.
    @Test
    fun testDataUnloaded(){
        val singletonDataManager:MainDataManager.SingletonDataManager = MainDataManager.SingletonDataManager(mockJsonReader)
        //Assert/AssertFalse : verifie que la valeur est vrai ou fausse.
        assertFalse(singletonDataManager.isDataInitialised())
    }

    //J'ai mock ma classe JsonReader. Les données sont désormais marquées comme chargées.
    @Test
    fun testDataLoad(){
        val singletonDataManager:MainDataManager.SingletonDataManager = MainDataManager.SingletonDataManager(mockJsonReader)
        singletonDataManager.loadData("test")
        verify(mockJsonReader).readJsonObject("test")
        assert(singletonDataManager.isDataInitialised())
    }

    //Recherche Relation avec succes
    @Test
    fun testRechRelFound(){
        val singletonDataManager:MainDataManager.SingletonDataManager = MainDataManager.SingletonDataManager(mockJsonReader)
        singletonDataManager.loadData("test")
        assertEquals(singletonDataManager.researchRelation(1,2), RelationModel("1","2", RelationDirection.DOWN.toString(), RelationMandatory.MANDATORY.toString(), "Test text"))
    }

    
    //Recherche Relation non trouvée
    @Test
    fun testRechRelNotFound(){
        val singletonDataManager:MainDataManager.SingletonDataManager = MainDataManager.SingletonDataManager(mockJsonReader)
        singletonDataManager.loadData("test")
        //assertEquals(singletonDataManager.researchRelation(1,2), RelationModel("1","2", RelationDirection.DOWN.toString(), RelationMandatory.MANDATORY.toString(), "Test text"))
        assertNotEquals(singletonDataManager.researchRelation(1,2),singletonDataManager.researchRelation(3,1))

    }

    //Je vérifie que la fonction equals de ma classe RElationmodel fonctionne correctement
    @Test
    fun testRelationModelEquals(){
        val singletonDataManager:MainDataManager.SingletonDataManager = MainDataManager.SingletonDataManager(mockJsonReader)
        singletonDataManager.loadData("test")
        assertNotEquals(singletonDataManager.researchRelation(1,2),"prout")
        assertNotEquals(singletonDataManager.researchRelation(1,2),singletonDataManager.researchRelation(3,1))
    }

}
