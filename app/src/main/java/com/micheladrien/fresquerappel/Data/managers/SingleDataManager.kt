package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.SingleCard

interface SingleDataManager {
    fun getCard(num : Int) : SingleCard?
}