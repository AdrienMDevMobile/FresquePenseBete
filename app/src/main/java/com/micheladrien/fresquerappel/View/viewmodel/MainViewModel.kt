package com.micheladrien.fresquerappel.View.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.micheladrien.fresquerappel.R


//Currently : only prints the name of the collage
class MainViewModel(application: Application) : AndroidViewModel(application){

    private val _name = MutableLiveData<String>().apply {
        value = application.getString(R.string.collage_climat)
    }

    val name : LiveData<String> = _name

    /*
    override fun notifyNewCollage(collage_name: String) {
        _name.value = collage_name
    }*/

    /*
    public fun changeCollage(name_requested:String){
        val new_name = settingManager.changeCollage(name_requested)
        if (name_requested == settingManager.NO_COLLAGE_STRING){
            Toast.makeText(getApplication(), getApplication<Application>().getString(R.string.toast_collage_unavailable, name), Toast.LENGTH_SHORT).show()
        }
        else
            _name.value = new_name
    }
    */
}