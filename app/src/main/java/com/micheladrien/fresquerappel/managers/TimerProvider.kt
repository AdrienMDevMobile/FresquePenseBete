package com.micheladrien.fresquerappel.managers

import com.micheladrien.fresquerappel.datas.TimerModel
import java.util.ArrayList

interface TimerProvider {

    fun getListTimer() : ArrayList<TimerModel>

}