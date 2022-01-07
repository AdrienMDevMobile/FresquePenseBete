package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.SingleCard
import com.micheladrien.fresquerappel.Data.providers.SingleDataProvider
import javax.inject.Inject

class SingleDataManagerMain @Inject constructor(provider : SingleDataProvider) : SingleDataManager {

    private val singleList  = provider.provideSingles()

    override fun getCard(num: Int): SingleCard {
        singleList.forEach{
            if(it.number == num) return it
        }

        return SingleCard("", num, 0, "")
    }
}