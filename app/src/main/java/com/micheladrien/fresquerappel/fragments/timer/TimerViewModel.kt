package com.micheladrien.fresquerappel.fragments.timer

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel
import com.micheladrien.fresquerappel.managers.TimerProvider
import com.micheladrien.fresquerappel.tools.TimerState
import com.micheladrien.fresquerappel.tools.notification.TimerSExecutor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
class TimerViewModel @Inject constructor(private val timerExecutor : TimerSExecutor, private val timerProvider: TimerProvider) : ViewModel() {

    var timerArrayList:ArrayList<TimerModel>? = null

    private val _timerLiveData = MutableLiveData<ArrayList<TimerModel>>().apply { false }
    val timerLiveData: LiveData<ArrayList<TimerModel>> = _timerLiveData

    private val _timerState = MutableLiveData<TimerState>()
    val timerState: LiveData<TimerState> = _timerState

    private var coroutineScope = this.viewModelScope


    public fun setCouroutineScope(coroutineScopeProvider: CoroutineScope){
        this.coroutineScope = coroutineScopeProvider
    }


    init{
        populateList()
    }

    //TODO Recuperer la liste depuis une BDD
    fun populateList(){
        populateList(timerProvider.getListTimer())
    }

    private fun populateList(timerArrayList : ArrayList<TimerModel>){
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

        coroutineScope.launch(Dispatchers.IO){
            if(timerExecutor.executeTimers(context, timerArrayList))
                _timerState.postValue(TimerState.STARTED)
            else _timerState.postValue(TimerState.ERROR)
        }



    }

    //TODO
    fun stopTimer(context : Context) {
        coroutineScope.launch(Dispatchers.IO) {
            if(timerExecutor.stopAllTimers(context))
                _timerState.postValue(TimerState.STOPPED)
            else _timerState.postValue(TimerState.ERROR)
        }
    }

}
