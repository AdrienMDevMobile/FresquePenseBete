package com.micheladrien.fresquerappel.fragments.timer

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.managers.TimerManager
import com.micheladrien.fresquerappel.tools.notification.TimerSExecutor
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.ArrayList
import javax.inject.Inject

//import il.co.theblitz.observablecollections.lists.ObservableArrayList

/*
Observable array list :  https://github.com/theblitz/ObservableCollections
https://stackoverflow.com/questions/7178801/how-do-i-structure-mvvm-with-collections
TODO Remplacer string par TimephaseViewModel
 */
//https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
@HiltViewModel
class TimerViewModel @Inject constructor(private val timerExecutor : TimerSExecutor) : ViewModel() {

    private val timerManager = TimerManager()

    var timerArrayList:ArrayList<TimerModel>? = null

    private val _timerLiveData = MutableLiveData<ArrayList<TimerModel>>()

    val timerLiveData: LiveData<ArrayList<TimerModel>> = _timerLiveData

    init{
        populateList()
    }

    //TODO Recuperer la liste depuis une BDD
    fun populateList(){
        populateList(timerManager.getListTimer())
    }

    fun populateList(timerArrayList : ArrayList<TimerModel>){
        this.timerArrayList = timerArrayList
        _timerLiveData.postValue(timerArrayList)
    }

    //TODO
    fun changeTimer(id: Int, new_name:String, new_time:Int){

    }

    //TODO
    fun suppressTimer(id:Int){

    }

    fun startTimer(context : Context){

        timerExecutor.executeTimers(context, timerArrayList)

        val toast = Toast.makeText(context, context.getString(R.string.timer_set_toast), Toast.LENGTH_SHORT)
        toast.show()
    }

    //TODO
    fun stopTimer() {

    }

}
