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
import il.co.theblitz.observablecollections.lists.ObservableArrayList

/*
Observable array list :  https://github.com/theblitz/ObservableCollections
https://stackoverflow.com/questions/7178801/how-do-i-structure-mvvm-with-collections
TODO Remplacer string par TimephaseViewModel
 */
class TimerViewModel(application: Application) : AndroidViewModel(application) {


    private val _timerCollection = ObservableArrayList<String>().apply {
        this.add("aa")
        this.add("bb")
        this.add("cc")
        this.add("dd")
    }

    val timerCollection : ObservableArrayList<String> = _timerCollection

    fun addTimer(){

    }

    fun removeTimer(){

    }

}
