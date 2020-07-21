package com.example.fresquerappel.ui.recherche

class RelationModel(val number1: Int, val number2: Int, val fresque: String, val relationDirection: RelationDirection, val explanation:String){

    companion object{
        fun research(number1:Int, number2:Int, fresque:String):RelationModel{
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

            return RelationModel(number1, number2, fresque, relationDirection , explication)
        }
    }


}

enum class RelationDirection {
    INCORRECT, UP, DOWN, NONE
}