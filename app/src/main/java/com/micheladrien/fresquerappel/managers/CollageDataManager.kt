package com.micheladrien.fresquerappel.managers

import com.micheladrien.fresquerappel.datas.CardsRelation
import com.micheladrien.fresquerappel.fragments.single.Single

interface CollageDataManager {

    fun changeCollage(name:String) //String = name of the collage
    fun isDataInitialised():Boolean
    fun researchRelation(number1:Int, number2:Int): CardsRelation
    fun researchSingle(number1:Int): Single
    fun getCurrentCollage() : String

}