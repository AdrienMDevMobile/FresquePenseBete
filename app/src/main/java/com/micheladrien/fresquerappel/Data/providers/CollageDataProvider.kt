package com.micheladrien.fresquerappel.Data.providers

import com.micheladrien.fresquerappel.Data.datas.CardsRelation

//This interface provides the data
interface CollageDataProvider {
    fun provideRelations(collage:String) : MutableList<CardsRelation>
}