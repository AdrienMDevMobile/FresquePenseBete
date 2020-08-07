package com.micheladrien.fresquerappel.ui.recherche

import android.content.Context
import com.micheladrien.fresquerappel.DataManager
import com.micheladrien.fresquerappel.tools.JsonReader

class RelationDataManager : DataManager{
        private var list: MutableList<RelationModel>? = null
        private var isInitialised:Boolean = false

        override fun initialize(context: Context, file_name:String){
            val jsonReader = JsonReader()
            list = jsonReader.readJsonObject(context, file_name)
            isInitialised = true
        }

        override fun isInitalised():Boolean{
            return this.isInitialised
        }

        override fun research(number1:Int, number2:Int, fresque:String):RelationModel{
            list?.forEach {
                if(it.number1 == number1){
                    if(it.number2 == number2)
                        return it
                }
            }
            return RelationModel(number1, number2, fresque, Relation(RelationDirection.NONE, RelationMandatory.OPTIONAL) , "")
        }

}