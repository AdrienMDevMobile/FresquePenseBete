package com.micheladrien.fresquerappel.Data.providers

import android.content.Context
import com.micheladrien.fresquerappel.Data.datas.CardsRelation
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONException
import javax.inject.Inject

open class CollageDataProviderJson @Inject constructor(@ApplicationContext val context:Context) : CollageDataProvider, DataProviderJSON(context) {

    override fun provideRelations(collage: String): MutableList<CardsRelation> = readJsonObject(collage.toLowerCase())


    open fun readJsonObject(file_name:String):MutableList<CardsRelation>{

        try {
            val jArray = JSONArray(loadJSONFromAsset(file_name))

            val list = mutableListOf<CardsRelation>()

            for (i in 0 until jArray.length()) {

                val card1: String =
                    jArray.getJSONObject(i).getString("c1")
                val card2: String =
                    jArray.getJSONObject(i).getString("c2")
                val direction: String =
                    jArray.getJSONObject(i).getString("rel")
                val mandatory:String =
                    jArray.getJSONObject(i).getString("mandatory")
                val explanation: String =
                    jArray.getJSONObject(i).getString("expl")

                val rel = CardsRelation(card1, card2, direction, mandatory, explanation)

                list.add(rel)
            }

            return list
        } catch (e: JSONException) {
            e.printStackTrace()
            return mutableListOf<CardsRelation>()
        }
    }




}
