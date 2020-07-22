package com.example.fresquerappel.tools

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream


class JsonReader {

    fun readJsonObject(context: Context){
        try {
            val jArray = JSONArray(loadJSONFromAsset(context))
            for (i in 0 until jArray.length()) {
                val card1: String =
                    jArray.getJSONObject(i).getString("c1")
                val card2: String =
                    jArray.getJSONObject(i).getString("c2")
                val relation: String =
                    jArray.getJSONObject(i).getString("rel")
                val explanation: String =
                    jArray.getJSONObject(i).getString("rel")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun loadJSONFromAsset(context: Context): String? {

        var json: String? = null
        json = try {
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
