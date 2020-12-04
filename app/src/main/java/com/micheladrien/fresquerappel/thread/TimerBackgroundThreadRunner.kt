package com.micheladrien.fresquerappel.thread

import com.micheladrien.fresquerappel.datas.TimerModel

class TimerBackgroundThreadRunner(val listeTimer : ArrayList<TimerModel>){
    fun start(){

    }

    fun stop(){

    }

    //TODO Repousser la fin d'une section de X minutes
    fun repeat(numberSecond : Int){

    }
    //TODO Etape 1, cela passera par la fonction sans param (int prédéfini), + tard, mettre son paramétrage à un endroid
    fun repeat(){
        this.repeat(300)
    }
}