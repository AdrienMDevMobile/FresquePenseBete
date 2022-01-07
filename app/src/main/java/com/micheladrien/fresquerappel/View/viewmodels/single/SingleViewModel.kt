package com.micheladrien.fresquerappel.View.viewmodels.single

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.Data.managers.SingleDataManager
import dagger.hilt.android.lifecycle.HiltViewModel

abstract class SingleViewModel(val dataManager: SingleDataManager, application: Application) :
    AndroidViewModel(application) {
        lateinit var cardName : LiveData<String>
        lateinit var explanation : LiveData<String>
        lateinit var setInt : LiveData<Int>
        lateinit var cardNumber : LiveData<Int>
        lateinit var cardImage : LiveData<Bitmap>

        abstract fun goToCard(num : Int)
        abstract fun goToPreviousCard()
        abstract fun goToNextCard()

}