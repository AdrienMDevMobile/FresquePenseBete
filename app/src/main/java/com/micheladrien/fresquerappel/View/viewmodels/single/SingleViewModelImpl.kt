package com.micheladrien.fresquerappel.View.viewmodels.single

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.micheladrien.fresquerappel.Data.managers.SingleDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingleViewModelImpl @Inject constructor (dataManager: SingleDataManager) :
    SingleViewModel(dataManager) {

    //Is here to manage the navigations arrows
    private var currentNumber = 1
    //Is only here to expose the number to the user
    private var _cardNumber = MutableLiveData<Int>(currentNumber)

    private val _cardName = MutableLiveData<String>("")
    private val _explanation = MutableLiveData<String>("")
    private val _setInt = MutableLiveData<Int>(0)

    init {
        cardName = _cardName
        explanation = _explanation
        setInt = _setInt
        cardNumber = _cardNumber

        goToCard(currentNumber)
    }

    override fun goToCard(num: Int) {
        var card = dataManager.getCard(num)
        _cardName.value = card.name
        _setInt.value = card.set
        _explanation.value = card.text
        changeNumber(num)
    }

    private fun changeNumber(num: Int) {
        currentNumber = num
        _cardNumber.value = num
    }

    override fun goToPreviousCard() =  goToCard(currentNumber -1)

    override fun goToNextCard() =  goToCard(currentNumber +1)


}