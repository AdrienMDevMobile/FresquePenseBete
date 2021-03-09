package com.micheladrien.fresquerappel.manager

import com.micheladrien.fresquerappel.datas.CardsRelation
import com.micheladrien.fresquerappel.fragment.single.Single

interface CollageDataManager {

    fun changeCollage(name:String) //String = name of the collage
    fun isDataInitialised():Boolean
    fun loadData(file_name:String) //String = name of the file
    fun researchRelation(number1:Int, number2:Int): CardsRelation
    fun researchSingle(number1:Int): Single

}