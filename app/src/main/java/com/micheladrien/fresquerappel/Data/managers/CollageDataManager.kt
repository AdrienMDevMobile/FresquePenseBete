package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.CardsRelation
import com.micheladrien.fresquerappel.View.view.single.Single

interface CollageDataManager {

    fun changeCollage(name:String) //String = name of the collage
    fun isDataInitialised():Boolean
    fun researchRelation(number1:Int, number2:Int): CardsRelation
    fun researchSingle(number1:Int): Single
    fun getCurrentCollage() : String

}