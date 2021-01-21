package com.micheladrien.fresquerappel.fragment.timer

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.manager.TimerManager
import com.micheladrien.fresquerappel.tools.notification.TimerService
import java.util.ArrayList

//import il.co.theblitz.observablecollections.lists.ObservableArrayList

/*
Observable array list :  https://github.com/theblitz/ObservableCollections
https://stackoverflow.com/questions/7178801/how-do-i-structure-mvvm-with-collections
TODO Remplacer string par TimephaseViewModel
 */
//https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
class TimerViewModel : ViewModel() {

    private val timerManager = TimerManager()
    var timerArrayList:ArrayList<TimerModel>? = null
    private val _timerLiveData = MutableLiveData<ArrayList<TimerModel>>()
    val timerLiveData: LiveData<ArrayList<TimerModel>> = _timerLiveData

    init{
        populateList()
    }

    //TODO Recuperer la liste depuis une BDD
    fun populateList(){
        timerArrayList = timerManager.getListTimer()
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

        val toast = Toast.makeText(context, context.getString(R.string.timer_set_toast), Toast.LENGTH_SHORT)
        toast.show()
    }

    //TODO
    fun stopTimer() {

    }

}
