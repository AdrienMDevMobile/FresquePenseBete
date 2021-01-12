package com.micheladrien.fresquerappel.fragment.timer

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.tools.notification.TimerService
import java.util.ArrayList

//import il.co.theblitz.observablecollections.lists.ObservableArrayList

/*
Observable array list :  https://github.com/theblitz/ObservableCollections
https://stackoverflow.com/questions/7178801/how-do-i-structure-mvvm-with-collections
TODO Remplacer string par TimephaseViewModel
 */
//https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
class TimerViewModel(application: Application) : AndroidViewModel(application) {



    private val _timerLiveData = MutableLiveData<ArrayList<TimerModel>>()
    var timerArrayList:ArrayList<TimerModel>? = null
    val timerLiveData: LiveData<ArrayList<TimerModel>> = _timerLiveData

    init{
        populateList()
    }

    //TODO Recuperer la liste depuis une BDD
    fun populateList(){
        /*
        val time1  = "Time1"
        val time2  = "Time2"
        val time3  = "Time3"
        */
        //timerArrayList = ArrayList<String>()

        val time1 = TimerModel(1, "1ab", 2)
        val time2 = TimerModel(2, "2a", 2)
        val time3 = TimerModel(3, "conclusion", 10)
        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time1)
        timerArrayList!!.add(time2)
        //timerArrayList!!.add(time3)

        _timerLiveData.value = timerArrayList
    }

    //TODO
    fun changeTimer(id: Int, new_name:String, new_time:Int){

    }

    //TODO
    fun suppressTimer(id:Int){

    }

    fun startTimer(context : Context){

        val mIntent = Intent(context, TimerService::class.java)
        //mIntent.putExtra("maxCountValue", 1000)
        mIntent.putParcelableArrayListExtra(TimerService.KEY_TIMERSERVICE_EXTRA,timerArrayList)
        TimerService.enqueueWork(context, mIntent)


    }

    //TODO
    fun stopTimer() {

    }

}
