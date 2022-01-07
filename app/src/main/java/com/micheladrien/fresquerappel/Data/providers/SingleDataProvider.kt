package com.micheladrien.fresquerappel.Data.providers

import com.micheladrien.fresquerappel.Data.datas.SingleCard

interface SingleDataProvider {
    fun provideSingles() : MutableList<SingleCard>
}