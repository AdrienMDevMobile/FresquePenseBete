package com.micheladrien.fresquerappel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class absMainVM: ViewModel() {
    abstract val name : LiveData<String>
    abstract val text: LiveData<String>
    abstract val carte1 : LiveData<Int>
    abstract val carte2 : LiveData<Int>
    abstract val relation : LiveData<String>
    abstract val relation_color : LiveData<Int>
    abstract val relation_mandatory : LiveData<String>

    abstract fun setContext(context: Context)

    abstract fun changeCards(carte1:Int, carte2:Int)
    abstract fun changeCollage(name:String)

    abstract fun research()
}