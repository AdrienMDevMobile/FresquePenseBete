package com.micheladrien.fresquerappel.manager

import android.content.Context
import com.micheladrien.fresquerappel.fragment.single.Single
import com.micheladrien.fresquerappel.datas.RelationModel

interface CollageDataManager {

    fun changeCollage(name:String) //String = name of the collage
    fun isDataInitialised():Boolean
    fun loadData(file_name:String) //String = name of the file
    fun researchRelation(number1:Int, number2:Int): RelationModel
    fun researchSingle(number1:Int): Single

}