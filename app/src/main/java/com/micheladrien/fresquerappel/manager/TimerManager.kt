package com.micheladrien.fresquerappel.manager

import com.micheladrien.fresquerappel.datas.TimerModel
import java.util.ArrayList

class TimerManager {

    fun getListTimer(): ArrayList<TimerModel>{

        var timerArrayList:ArrayList<TimerModel>?
        val time1 = TimerModel(1, "Lot 1", 600)
        val time2 = TimerModel(2, "Lot 2", 900)
        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time1)
        timerArrayList!!.add(time2)

        return timerArrayList
    }
}