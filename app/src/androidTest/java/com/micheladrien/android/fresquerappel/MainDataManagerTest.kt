package com.micheladrien.android.fresquerappel

import com.micheladrien.fresquerappel.datas.RelationModel
import com.micheladrien.fresquerappel.manager.MainDataManager
import com.micheladrien.fresquerappel.tools.JsonReader
import junit.framework.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainDataManagerTest {


    @Mock
    val mockJsonReader: JsonReader = mock(JsonReader::class.java)

    @Before
    fun set_up() {
        //Line that doesn't work
        `when`(mockJsonReader.readJsonObject("test")).thenReturn(mutableListOf<RelationModel>())
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
}
