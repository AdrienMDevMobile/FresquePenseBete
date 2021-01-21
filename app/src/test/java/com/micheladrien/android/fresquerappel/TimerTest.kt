package com.micheladrien.android.fresquerappel


import com.micheladrien.fresquerappel.fragment.timer.TimerAdapter
import com.micheladrien.fresquerappel.tools.TimerStringTool
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimerTest {

    /* Test outil conversion secondes en texte heures:minutes:secondes - Debut */
    /*fromSecToTimeString et fromTimeStringToSec */
    @Test
    fun testSecondToTimeString(){
        val seconds = 3661 //Une heure une minute et une seconde
        val text = TimerStringTool.fromSecToTimeString(seconds)
        assertEquals(text, "01:01:01")
    }

    @Test
    fun testSecondToTimeString2(){
        val seconds = 61 //Une minute et une seconde
        val text = TimerStringTool.fromSecToTimeString(seconds)
        assertEquals(text, "01:01")
    }

    @Test
    fun testTimeStringToSecond(){
        val testText = "01:01:01"
        val seconds = TimerStringTool.fromTimeStringToSec(testText)
        assertEquals(seconds, 3661)
    }

    @Test
    fun testTimeStringToSecond2(){
        val testText = "01:01"
        val seconds = TimerStringTool.fromTimeStringToSec(testText)
        assertEquals(seconds, 61)
    }
    /* Test outil conversion secondes en texte heures:minutes:secondes - Fin */
}