package com.micheladrien.fresquerappel.datas

import android.annotation.SuppressLint
import java.util.*

class RelationModel(val number1: Int, val number2: Int, val relation: Relation, val explanation:String){

    constructor(string1:String, string2:String, rDirString: String, rMandatoryString:String, explanation: String) : this(string1.toInt(), string2.toInt(), Relation(
        RelationDirection.getRelDirection(rDirString), RelationMandatory.getRelMandatory(rMandatoryString)
    ), explanation) {}

    override fun equals(other:Any?): Boolean{
        return other is RelationModel && number1 == other.number1 && number2 == other.number2 && relation == other.relation && explanation == other.explanation

    }

}

class Relation(val direction: RelationDirection, val mandatory: RelationMandatory){
    override fun equals(other: Any?): Boolean {
        return other is Relation && direction == other.direction && mandatory == other.mandatory
    }
}

enum class RelationDirection {
    INCORRECT, UP, DOWN, UPDOWN, NONE;

    companion object{
        @SuppressLint("DefaultLocale")
        fun getRelDirection(relationDirection: String) : RelationDirection {
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
        @SuppressLint("DefaultLocale")
        fun getRelMandatory(relationMandatory:String) : RelationMandatory {
            when(relationMandatory.toUpperCase()){
                "MANDATORY"-> return MANDATORY
            }
            return OPTIONAL
        }
    }
}