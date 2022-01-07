package com.micheladrien.fresquerappel.Data.providers

import android.content.Context
import java.io.IOException
import java.io.InputStream

abstract class DataProviderJSON (private val context: Context) {

    protected fun loadJSONFromAsset(file_name:String): String? {
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