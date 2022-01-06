package com.micheladrien.android.fresquerappel.view

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import com.micheladrien.android.fresquerappel.CustomCoroutineRule
//import com.micheladrien.android.fresquerappel.CustomCoroutineRule
import com.micheladrien.fresquerappel.View.view.Main_activity
import com.micheladrien.fresquerappel.Data.datas.TimerModel
import com.micheladrien.fresquerappel.View.viewmodel.timer.TimerViewModel
import com.micheladrien.fresquerappel.Data.managers.TimerProvider
import com.micheladrien.fresquerappel.View.tools.notification.TimerSExecutor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//https://www.vogella.com/tutorials/Mockito/article.html


/*
https://proandroiddev.com/how-to-easily-test-a-viewmodel-with-livedata-and-coroutines-230c74416047
Problème : si il y a un callback : cela vérifie bien la valeur.
Si il n'y a rien. Cela se termine de manière positive.

https://medium.com/@muratcanbur/unit-test-your-livedata-and-viewmodel-3b224f71e981
Mocking observer.
*/
@RunWith(JUnit4::class)
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
    @get:Rule val coroutineRule = CustomCoroutineRule()


    lateinit var timerProvider: TimerProvider
    private lateinit var mockTimerSExecutor: TimerSExecutor
    private lateinit var context: Context
    private lateinit var vm : TimerViewModel
    private val fakeListTimer = ArrayList<TimerModel>()



    @Before
    fun init() {
        timerProvider =  mockk()
        mockTimerSExecutor = mockk()
        every { timerProvider.getListTimer() } returns fakeListTimer
        context = ApplicationProvider.getApplicationContext()

        vm = TimerViewModel(mockTimerSExecutor, timerProvider)
        vm.setCouroutineScope( TestCoroutineScope(TestCoroutineDispatcher()))
    }

    /*
    @Test
    fun testInit(){
        assertTrue(true)
    }*/

    //Préparer un test avant de dev la classe :
    //je mock/fake les classes utilisée par ma classe a creer
    //et je m'assure que la fonction des classes utilisée soit bien appelé
    @Test
    fun testCallSExecutor() = coroutineRule.runBlockingTest{
        vm.startTimer(context)
        //Verify(mockTimerSExecutor).executeTimers(context, fakeListTimer)
        //assertTrue(true)
       coVerify{mockTimerSExecutor.executeTimers(context, fakeListTimer)}
    }

    @Test
    fun stopTimers(){
        vm.startTimer(context)
        vm.stopTimer(context)
        coVerify {mockTimerSExecutor.stopAllTimers(context, fakeListTimer)}
    }

}