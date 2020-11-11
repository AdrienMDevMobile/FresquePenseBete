package com.micheladrien.fresquerappel.manager

import android.content.Context
import com.micheladrien.fresquerappel.datas.Settings
import com.micheladrien.fresquerappel.tools.WaitingViewModel

class SettingsManager(var context : Context, var dataManager : DataManager) {

    private val settings : Settings = Settings("")
    private val listOfViewModel : ArrayList<WaitingViewModel> = ArrayList<WaitingViewModel>()

    fun addWaitingVM(vm : WaitingViewModel){
        listOfViewModel.add(vm)
    }

    //Notify the VMs that the collage is changed (currently, only MainViewModel)
    fun changeCollage(name:String): String {
        dataManager.loadData(name.toLowerCase())
        //return NO_COLLAGE_STRING servira en cas d'erreur d'initialisation connues

        for (vm in listOfViewModel){
            vm.notifyNewCollage(name)
        }

        return name
    }
}