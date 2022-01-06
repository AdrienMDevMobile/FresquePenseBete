//Singleton : https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
package com.micheladrien.fresquerappel.Data.managers

import com.micheladrien.fresquerappel.Data.datas.*
import com.micheladrien.fresquerappel.Data.datas.SingleCard
import javax.inject.Inject

class CollageDataManagerMain @Inject constructor(private val collageDataProvider: CollageDataProvider) : CollageDataManager {



   init{
       loadCollage("Climat")
   }


    //Subclass. Cette classe ne sera instanciée qu'une fois

    private lateinit var currentCollage : String
    private var list: MutableList<CardsRelation>? = null
    private var is_list_init: Boolean = false


    private fun loadCollage(name: String) {
        list = collageDataProvider.provideRelations(name)
        currentCollage = name
        is_list_init = true
    }

    private fun isDataInitialised(): Boolean {
        return this.is_list_init
    }

    override fun researchRelation(number1: Int, number2: Int): CardsRelation {
        list?.forEach {
            if (it.number1 == number1) {
                if (it.number2 == number2)
                    return it
            }
        }
        return CardsRelation(
            number1,
            number2,
            Relation(RelationDirection.NONE, RelationMandatory.OPTIONAL),
            ""
        )
    }

    override fun researchSingle(number1: Int): SingleCard {
        TODO("Not yet implemented")
    }

}
