package com.micheladrien.fresquerappel.manager

import com.micheladrien.fresquerappel.datas.TimerModel
import java.util.ArrayList

class TimerManager {

    companion object {
        val TEST_VALUES = "TEST"
        val RUN_VALUES = "RUN"
    }

    fun getListTimer(): ArrayList<TimerModel>{

        var timerArrayList: ArrayList<TimerModel>?
        val time1 = TimerModel(1, "Lot 1", 2)
        val time2 = TimerModel(2, "Lot 2", 3)
        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time1)
        timerArrayList!!.add(time2)

        return timerArrayList

    }

    fun getTestListTimer() : ArrayList<TimerModel>{
        var timerArrayList:ArrayList<TimerModel>?
        val time1 = TimerModel(1, "Lot 1", 1)

        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time1)

        return timerArrayList
    }
}