package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.CardsRelation

//This interface provides the data
interface DataProvider {
    fun provideRelations(collage:String) : MutableList<CardsRelation>
}