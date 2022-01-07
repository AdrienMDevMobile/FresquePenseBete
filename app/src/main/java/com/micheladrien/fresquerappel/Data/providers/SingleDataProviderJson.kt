package com.micheladrien.fresquerappel.Data.providers

import android.content.Context
import com.micheladrien.fresquerappel.Data.datas.CardsRelation
import com.micheladrien.fresquerappel.Data.datas.SingleCard
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONException
import javax.inject.Inject

class SingleDataProviderJson @Inject constructor(@ApplicationContext val context: Context) : SingleDataProvider, DataProviderJSON(context) {
    override fun provideSingles(): MutableList<SingleCard> = readJsonObject("single_fr")

    private fun readJsonObject(file_name:String): MutableList<SingleCard>{
        try {
            val jArray = JSONArray(loadJSONFromAsset(file_name))

            val list = mutableListOf<SingleCard>()

            for (i in 0 until jArray.length()) {

                val name: String =
                    jArray.getJSONObject(i).getString("name")
                val num: Int =
                    jArray.getJSONObject(i).getInt("num")
                val set: Int =
                    jArray.getJSONObject(i).getInt("set")
                val text:String =
                    jArray.getJSONObject(i).getString("text")

                val card = SingleCard(name, num, set, text)

                list.add(card)
        }
            return list
        } catch (e: JSONException) {
            e.printStackTrace()
            return mutableListOf<SingleCard>()
        }
    }

}