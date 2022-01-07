package com.micheladrien.fresquerappel.View.viewmodels.single

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.Data.managers.SingleDataManager
import dagger.hilt.android.lifecycle.HiltViewModel

abstract class SingleViewModel(val dataManager: SingleDataManager) :
    ViewModel() {
        lateinit var cardName : LiveData<String>
        lateinit var explanation : LiveData<String>
        lateinit var setInt : LiveData<Int>
        lateinit var cardNumber : LiveData<Int>

        abstract fun goToCard(num : Int)
        abstract fun goToPreviousCard()
        abstract fun goToNextCard()

}