package com.micheladrien.fresquerappel

import android.content.Context
import com.micheladrien.fresquerappel.ui.recherche.RelationModel

interface DataManager {

    fun isInitalised():Boolean
    fun initialize(context: Context, file_name:String)
    fun research(number1:Int, number2:Int, fresque:String): RelationModel
}