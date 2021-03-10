//Singleton : https://blog.mindorks.com/how-to-create-a-singleton-class-in-kotlin
package com.micheladrien.fresquerappel.managers

import com.micheladrien.fresquerappel.datas.*
import com.micheladrien.fresquerappel.fragments.single.Single
import javax.inject.Inject

class MainCollageDataManager @Inject constructor(private val dataProvider: DataProvider) : CollageDataManager {

   init{
       if (!isDataInitialised()){
           loadData("climat")
           //loadData(init_collage)
           //loadData(context.getString(R.string.collage_climat))
       }
   }


    //Subclass. Cette classe ne sera instanciée qu'une fois


    private var list: MutableList<CardsRelation>? = null
    private var is_list_init: Boolean = false

    override fun loadData(file_name: String) {
        list = dataProvider.provideRelations(file_name)
        is_list_init = true
    }

    override fun changeCollage(name: String) {
        loadData(name)
    }

    override fun isDataInitialised(): Boolean {
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

    override fun researchSingle(number1: Int): Single {
        TODO("Not yet implemented")
    }
}
