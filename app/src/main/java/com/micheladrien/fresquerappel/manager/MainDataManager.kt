package com.micheladrien.fresquerappel.manager

import android.content.Context
import com.micheladrien.fresquerappel.tools.JsonReader
import com.micheladrien.fresquerappel.fragment.single.Single
import com.micheladrien.fresquerappel.datas.Relation
import com.micheladrien.fresquerappel.datas.RelationDirection
import com.micheladrien.fresquerappel.datas.RelationMandatory
import com.micheladrien.fresquerappel.datas.RelationModel

class MainDataManager : DataManager {
    private var list: MutableList<RelationModel>? = null
    private var isInitialised:Boolean = false

    override fun initialize(context: Context, file_name : String){
        val jsonReader = JsonReader()
        list = jsonReader.readJsonObject(context, file_name)
        isInitialised = true
    }

    override fun isInitalised():Boolean{
        return this.isInitialised
    }

    override fun researchRelation(number1:Int, number2:Int): RelationModel {
        list?.forEach {
            if(it.number1 == number1){
                if(it.number2 == number2)
                    return it
            }
        }
        return RelationModel(number1, number2, Relation(RelationDirection.NONE, RelationMandatory.OPTIONAL) , "")
    }

    override fun researchSingle(number1: Int): Single {
        TODO("Not yet implemented")
    }

}