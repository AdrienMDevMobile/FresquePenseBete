package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.TimerModel
import java.util.ArrayList
import javax.inject.Inject

class TimerProviderRaw @Inject constructor(): TimerProvider{


    override fun getListTimer(): ArrayList<TimerModel> {

        var timerArrayList: ArrayList<TimerModel>?
        val time0 = TimerModel(1, "Passer au lot 2", 900)
        val time1 = TimerModel(2, "Passer au lot 3", 1200)
        val time2 = TimerModel(3, "Passer au lot 4 puis 5", 1200)
        val time3 = TimerModel(4, "Fin de l'atelier", 2100)
        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time0)
        timerArrayList!!.add(time1)
        timerArrayList!!.add(time2)
        timerArrayList!!.add(time3)

        return timerArrayList

    }

    /*
    override fun getListTimer() : ArrayList<TimerModel> {
        var timerArrayList: ArrayList<TimerModel>?
        val time1 = TimerModel(1, "Lot 1", 100)

        timerArrayList = ArrayList<TimerModel>()

        timerArrayList!!.add(time1)

        return timerArrayList
    }*/

}