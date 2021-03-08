package com.micheladrien.fresquerappel.manager

import com.micheladrien.fresquerappel.datas.CardsRelation

//This interface provides the data
interface DataProvider {
    fun provideRelations(collage:String) : MutableList<CardsRelation>
}