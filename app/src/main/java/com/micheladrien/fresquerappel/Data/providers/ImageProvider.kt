package com.micheladrien.fresquerappel.Data.providers

import android.content.Context
import android.graphics.Bitmap

interface ImageProvider {
    fun getCardImage(context : Context, number : Int) : Bitmap
}