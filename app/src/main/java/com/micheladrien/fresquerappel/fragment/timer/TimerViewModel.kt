package com.micheladrien.fresquerappel.fragment.timer

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.manager.DataManager
import com.micheladrien.fresquerappel.manager.MainDataManager
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.Relation
import com.micheladrien.fresquerappel.datas.RelationDirection
import com.micheladrien.fresquerappel.datas.RelationMandatory
//import il.co.theblitz.observablecollections.lists.ObservableArrayList

/*
Observable array list :  https://github.com/theblitz/ObservableCollections
https://stackoverflow.com/questions/7178801/how-do-i-structure-mvvm-with-collections
TODO Remplacer string par TimephaseViewModel
 */
//https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
class TimerViewModel(application: Application) : AndroidViewModel(application) {



    private val _timerLiveData = MutableLiveData<ArrayList<String>>()
    var timerArrayList:ArrayList<String>? = null
    val timerLiveData: LiveData<ArrayList<String>> = _timerLiveData

    init{
        populateList()
    }

    //TODO Recuperer la liste depuis une BDD
    fun populateList(){
        val time1  = "Time1"
        val time2  = "Time2"
        val time3  = "Time3"
        /*
        val time1 = TimerModel(1, "1", 15)
        val time2 = TimerModel(1, "2", 15)
        val time3 = TimerModel(1, "conclusion", 15)
        timerArrayList = ArrayList<TimerModel>()

        */
        timerArrayList = ArrayList<String>()
        timerArrayList!!.add(time1)
        timerArrayList!!.add(time2)
        timerArrayList!!.add(time3)

        _timerLiveData.value = timerArrayList
    }

    //TODO
    fun changeTimer(id: Int, new_name:String, new_time:Int){

    }

    //TODO
    fun suppressTimer(id:Int){

    }

}
