package com.micheladrien.fresquerappel.managers

import android.content.Context
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.CardsRelation
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

open class JsonDataProvider @Inject constructor(@ApplicationContext private val context:Context) : DataProvider {

    override fun provideRelations(collage: String): MutableList<CardsRelation> = readJsonObject(collage.toLowerCase())


    private fun readJsonObject(file_name:String):MutableList<CardsRelation>{

        try {
            val jArray = JSONArray(loadJSONFromAsset(file_name))
            //val jArray = JSONArray("[{\"c1\": 1,\"c2\": 2,\"rel\": \"UP\",\"mandatory\": \"mandatory\",\"expl\": \"\"}]")
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

    private fun loadJSONFromAsset(file_name:String): String? {
        val low_file_name : String = file_name.toLowerCase()

        val json = try {
            val assetManager = context.applicationContext.assets
            val `is`: InputStream = assetManager.open("json/" + low_file_name + ".json")
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            //Nous renvoyons le string pour json
            String(buffer, Charsets.UTF_8)
        }catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json

    }



}
