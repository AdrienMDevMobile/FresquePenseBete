package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.CardsRelation
import com.micheladrien.fresquerappel.Data.datas.SingleCard

interface CollageDataManager {

    fun researchRelation(number1:Int, number2:Int): CardsRelation
    fun researchSingle(number1:Int): SingleCard

}