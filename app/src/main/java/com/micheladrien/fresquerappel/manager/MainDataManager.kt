//Singleton : https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
package com.micheladrien.fresquerappel.manager

import android.content.Context
import android.util.Log
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.Relation
import com.micheladrien.fresquerappel.datas.RelationDirection
import com.micheladrien.fresquerappel.datas.RelationMandatory
import com.micheladrien.fresquerappel.datas.RelationModel
import com.micheladrien.fresquerappel.fragment.single.Single
import com.micheladrien.fresquerappel.tools.JsonReader

class MainDataManager(context: Context) : DataManager {
    val dataManager : SingletonDataManager = SingletonDataManager.getInstance(context)


   init{
       if (!isDataInitialised()){
           loadData(context.getString(R.string.collage_climat))
       }
   }

    override fun isDataInitialised(): Boolean {
        return dataManager.isDataInitialised()
    }

    override fun loadData(file_name: String) {
        synchronized(dataManager){
            dataManager.loadData(file_name)
        }

    }

    override fun researchRelation(number1: Int, number2: Int): RelationModel {
        synchronized(dataManager) {
            return dataManager.researchRelation(number1, number2)
        }
    }

    override fun researchSingle(number1: Int): Single {
        synchronized(dataManager){
            return dataManager.researchSingle(number1)}
    }



    //Subclass. Cette classe ne sera instanciée qu'une fois
    class SingletonDataManager(val jsonReader: JsonReader){

        companion object {
            lateinit private var instance : SingletonDataManager
            var boolInit:Boolean = false

            private fun isInitialised():Boolean{
                return boolInit
            }

            fun getInstance(context: Context): SingletonDataManager{
                if(isInitialised()) {
                    return instance
                }
                else {
                    val newJsonReader = JsonReader(context)
                    instance = SingletonDataManager(newJsonReader)
                    boolInit = true
                    return instance
                }
            }
        }

        private var list: MutableList<RelationModel>? = null
        private var is_list_init:Boolean = false


        fun loadData(file_name : String){
            list = jsonReader.readJsonObject(file_name)
            Log.d("ami", "Nous avons init la bdd")
            is_list_init = true
        }

        fun isDataInitialised():Boolean{
            return this.is_list_init
        }

        fun researchRelation(number1:Int, number2:Int): RelationModel {
            list?.forEach {
                if(it.number1 == number1){
                    if(it.number2 == number2)
                        return it
                }
            }
            return RelationModel(number1, number2, Relation(RelationDirection.NONE, RelationMandatory.OPTIONAL) , "")
        }

        fun researchSingle(number1: Int): Single {
            TODO("Not yet implemented")
        }
    }
}