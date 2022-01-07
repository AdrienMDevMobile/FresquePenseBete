package com.micheladrien.fresquerappel.Data.providers

import com.micheladrien.fresquerappel.Data.datas.TimerModel
import java.util.ArrayList

interface TimerProvider {

    fun getListTimer() : ArrayList<TimerModel>

}