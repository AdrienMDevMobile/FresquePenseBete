package com.micheladrien.fresquerappel.managers

import com.micheladrien.fresquerappel.datas.TimerModel
import java.util.ArrayList
import javax.inject.Inject

class RawTimerProvider @Inject constructor(): TimerProvider{

    /*
    override fun getListTimer(): ArrayList<TimerModel> {

        var timerArrayList: ArrayList<TimerModel>?
        val time0 = TimerModel(1, "Lot 1", 900)
        val time1 = TimerModel(2, "Lot 2", 1200)
        val time2 = TimerModel(1, "Lot 3", 1200)
        val time3 = TimerModel(2, "Lot 4+5", 2100)
        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time0)
        timerArrayList!!.add(time1)
        timerArrayList!!.add(time2)
        timerArrayList!!.add(time3)

        return timerArrayList

    }*/


    override fun getListTimer() : ArrayList<TimerModel> {
        var timerArrayList: ArrayList<TimerModel>?
        val time1 = TimerModel(1, "Lot 1", 1)

        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time1)

        return timerArrayList
    }

}