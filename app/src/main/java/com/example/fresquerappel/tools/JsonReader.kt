package com.example.fresquerappel.tools

import android.content.Context
import android.provider.ContactsContract
import com.example.fresquerappel.ui.recherche.RelationDirection
import com.example.fresquerappel.ui.recherche.RelationModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream


class JsonReader {

    fun readJsonObject(context: Context):MutableList<RelationModel>{
        val fresque = "climat"

        try {
            val jArray = JSONArray(loadJSONFromAsset(context))
            val list = mutableListOf<RelationModel>()

            for (i in 0 until jArray.length()) {

                val card1: String =
                    jArray.getJSONObject(i).getString("c1")
                val card2: String =
                    jArray.getJSONObject(i).getString("c2")
                val direction: String =
                    jArray.getJSONObject(i).getString("rel")
                val explanation: String =
                    jArray.getJSONObject(i).getString("expl")

                val rel = RelationModel(card1, card2, fresque, direction, explanation)

                list.add(rel)
            }

            return list
        } catch (e: JSONException) {
            e.printStackTrace()
            return mutableListOf<RelationModel>()
        }
    }

    private fun loadJSONFromAsset(context: Context): String? {

        val json = try {
            val assetManager = context.assets
            val `is`: InputStream = assetManager.open("json/climat.json")
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
