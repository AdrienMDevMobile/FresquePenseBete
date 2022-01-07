package com.micheladrien.android.fresquerappel

import com.micheladrien.fresquerappel.Data.datas.SingleCard
import com.micheladrien.fresquerappel.Data.managers.SingleDataManager
import com.micheladrien.fresquerappel.Data.managers.SingleDataManagerMain
import com.micheladrien.fresquerappel.Data.providers.SingleDataProvider
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class SingleDataManagerTest {

    private val mockedSingleDataProvider: SingleDataProvider = Mockito.mock(SingleDataProvider::class.java)

    lateinit var singleDataManager : SingleDataManager

    @Before
    fun set_up(){
        val singleList = mutableListOf<SingleCard>()
        singleList.add(
            SingleCard("Name1", 1,  1, "Text1")
        )

        `when`(mockedSingleDataProvider.provideSingles()).thenReturn(singleList)

        singleDataManager = SingleDataManagerMain(mockedSingleDataProvider)
    }

    @Test
    fun doesLoad(){
        verify(mockedSingleDataProvider).provideSingles()
    }

    @Test
    fun loadCard1(){
        var toTest = singleDataManager.getCard(1)
        assertNotNull(toTest)
        toTest?.let {
            assert(toTest.name == "Name1")
            assert(toTest.set == 1)
            assert(toTest.text == "Text1")
        }
    }

    @Test
    fun loadCardDoesntExist(){
        var toTest = singleDataManager.getCard(9999999)
        assertNull(toTest)
    }
}