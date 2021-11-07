package com.micheladrien.android.fresquerappel

import com.micheladrien.fresquerappel.Data.managers.ImageProviderRaw
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImageProviderRawTest {
    val toTest : ImageProviderRaw  = ImageProviderRaw()

    @Test
    fun testImageLocationName(){
        assertEquals("cardsImage/fr_adulte_carte_2_recto.png", toTest.getCardNameLocation(2))
    }
}