package com.micheladrien.fresquerappel.ui.recherche

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.micheladrien.fresquerappel.tools.JsonReader

class RelationModel(val number1: Int, val number2: Int, val fresque: String, val relation: Relation, val explanation:String){

    constructor(string1:String, string2:String, fresque: String, rDirString: String, rMandatoryString:String, explanation: String) : this(string1.toInt(), string2.toInt(), fresque, Relation(RelationDirection.getRelDirection(rDirString), RelationMandatory.getRelMandatory(rMandatoryString)), explanation) {}

    companion object{
        var list: MutableList<RelationModel>? = null

        fun initialize(context:Context){
            val jsonReader = JsonReader()
            list = jsonReader.readJsonObject(context)
        }

        fun research(number1:Int, number2:Int, fresque:String):RelationModel{

            list?.forEach {
                if(it.number1 == number1){
                    if(it.number2 == number2)
                        return it
                }
            }
            return RelationModel(number1, number2, fresque, Relation(RelationDirection.NONE, RelationMandatory.OPTIONAL) , "")

            /*
               var relationDirection = RelationDirection.NONE
               if(number1 == 1){
                   relationDirection = RelationDirection.UP
               }
               else if(number1 == 2){
                   relationDirection = RelationDirection.DOWN
               }
               else if(number1 == 3){
                   relationDirection = RelationDirection.INCORRECT
               }

               var explication = "Voici votre explication"
               if(number1 == 1 && number2==5)
                   explication = "On peut se demander si les activités humaines consomment des énegie fossiles ou si les énérgies fossiles permettent les activités humaines.\n Ce débat ne doit pas prendre de temps et on peut regrouper les deux cartes au besoin."

               return RelationModel(number1, number2, fresque, relationDirection , explication) */

        }

    }


}

class Relation(val direction:RelationDirection, val mandatory: RelationMandatory){
}

enum class RelationDirection {
    INCORRECT, UP, DOWN, UPDOWN, NONE;

    companion object{
        @SuppressLint("DefaultLocale")
        fun getRelDirection(relationDirection: String) : RelationDirection{
            when (relationDirection.toUpperCase()) {
                "UP" -> return UP
                "DOWN" -> return DOWN
                "UPDOWN"-> return UPDOWN
                "INCORRECT" -> return INCORRECT
            }
            return NONE
        }
    }
}

enum class RelationMandatory{
    MANDATORY, OPTIONAL;
    companion object{
        fun getRelMandatory(relationMandatory:String) : RelationMandatory{
            when(relationMandatory.toUpperCase()){
                "MANDATORY"-> return MANDATORY
            }
            return OPTIONAL
        }
    }
}