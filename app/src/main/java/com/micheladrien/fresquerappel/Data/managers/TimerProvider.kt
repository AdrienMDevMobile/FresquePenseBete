package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.TimerModel
import java.util.ArrayList

interface TimerProvider {

    fun getListTimer() : ArrayList<TimerModel>

}