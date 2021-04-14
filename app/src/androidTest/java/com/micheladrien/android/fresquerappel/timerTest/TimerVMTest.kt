package com.micheladrien.android.fresquerappel.timerTest

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.micheladrien.android.fresquerappel.diTest.testClasses.TestTimerProvider
import com.micheladrien.android.fresquerappel.observeForTesting
import com.micheladrien.fresquerappel.Main_activity
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.fragments.timer.TimerViewModel
import com.micheladrien.fresquerappel.managers.RawTimerProvider
import com.micheladrien.fresquerappel.managers.TimerProvider
import com.micheladrien.fresquerappel.tools.TimerState
import com.micheladrien.fresquerappel.tools.notification.TimerSExecutor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyListOf
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

//https://www.vogella.com/tutorials/Mockito/article.html


/*
https://proandroiddev.com/how-to-easily-test-a-viewmodel-with-livedata-and-coroutines-230c74416047
Problème : si il y a un callback : cela vérifie bien la valeur.
Si il n'y a rien. Cela se termine de manière positive.

https://medium.com/@muratcanbur/unit-test-your-livedata-and-viewmodel-3b224f71e981
Mocking observer.
 */
@RunWith(MockitoJUnitRunner::class)
@HiltAndroidTest
class TimerVMTest {

    @get:Rule
    val mainActivityTestRule : ActivityTestRule<Main_activity> = ActivityTestRule<Main_activity>(
            Main_activity::class.java
    )
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var timerProvider: TimerProvider
    @Mock
    private val mockTimerSExecutor: TimerSExecutor = Mockito.mock(TimerSExecutor::class.java)
    private lateinit var context: Context
    private lateinit var vm : TimerViewModel
    private val fakeListTimer = ArrayList<TimerModel>()



    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        `when`(timerProvider.getListTimer()).thenReturn(fakeListTimer)
        context = ApplicationProvider.getApplicationContext()

        vm = TimerViewModel(mockTimerSExecutor, timerProvider)

    }

    //Préparer un test avant de dev la classe :
    //je mock/fake les classes utilisée par ma classe a creer
    //et je m'assure que la fonction des classes utilisée soit bien appelé
    @Test
    fun testCallSExecutor(){
        vm.startTimer(context)
        verify(mockTimerSExecutor).executeTimers(context, fakeListTimer)
    }

    //we successfully get an answer from the start of the timers
    @Test
    fun getStartCallBack(){
        vm.startTimer(context)
        vm.timerState.observeForTesting {
            assertEquals(it.values[0], TimerState.STARTED)
        }
    }

    //@TODO
    @Test
    fun stopTimers(){
        vm.startTimer(context)
        vm.stopTimer(context)
        verify(mockTimerSExecutor).stopAllTimers(context)
    }

    //we successfully get an answer from the start of the timers
    //TODO
    @Test
    fun getStopCallBack(){
        vm.startTimer(context)
        vm.timerState.observeForTesting {
            assertEquals(it.values[0], TimerState.STOPPED)
        }
    }
}