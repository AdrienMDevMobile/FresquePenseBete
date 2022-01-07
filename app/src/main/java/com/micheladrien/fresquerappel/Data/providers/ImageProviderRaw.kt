package com.micheladrien.fresquerappel.Data.providers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream
import javax.inject.Inject

class ImageProviderRaw @Inject constructor() : ImageProvider {

    override fun getCardImage(context : Context, number: Int) : Bitmap {
        context?.let {
            val assetManager  = it.assets
            val `is`: InputStream = assetManager.open(getCardNameLocation(number))
            val bitmap = BitmapFactory.decodeStream(`is`)
            return bitmap
        }
    }

    fun getCardNameLocation(number: Int) : String{
        return  "cardsImage/fr_adulte_carte_${number}_recto.png"
    }
}