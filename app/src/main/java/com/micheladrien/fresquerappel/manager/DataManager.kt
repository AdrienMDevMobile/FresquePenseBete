package com.micheladrien.fresquerappel.manager

import android.content.Context
import com.micheladrien.fresquerappel.fragment.single.Single
import com.micheladrien.fresquerappel.datas.RelationModel

interface DataManager {

    fun isDataInitialised():Boolean
    fun loadData(file_name:String)
    fun researchRelation(number1:Int, number2:Int): RelationModel
    fun researchSingle(number1:Int): Single
}