package com.micheladrien.android.fresquerappel.diTest.testClasses

import com.micheladrien.fresquerappel.Data.datas.TimerModel
import com.micheladrien.fresquerappel.Data.providers.TimerProvider
import javax.inject.Inject

open class TimerProviderTest @Inject constructor(): TimerProvider {
    override fun getListTimer() : ArrayList<TimerModel> {
        var timerArrayList: ArrayList<TimerModel>?
        val time1 = TimerModel(1, "Lot 1", 1)

        timerArrayList = ArrayList<TimerModel>()

        timerArrayList.add(time1)

        return timerArrayList
    }
}