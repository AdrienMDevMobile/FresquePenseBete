package com.micheladrien.fresquerappel.View.tools

import java.lang.Exception
import kotlin.math.pow

class TimerStringTool {

    companion object{
        //From seconds to time string
        fun fromSecToTimeString(valueSec: Int) : String{
            val hours = valueSec/3600
            val minutes = (valueSec - hours * 3600) / 60
            val seconds = valueSec - hours * 3600 - minutes * 60


            var toReturn = ""

            val secondsStr = (if (seconds < 10) "0" else "") + seconds
            toReturn = secondsStr

            val minutesStr = (if (minutes < 10) "0" else "") + minutes
            toReturn = minutesStr + ":" + toReturn

            //Log.d("test conversion s", secondsStr)
            //Log.d("test conversion m", minutesStr)

            if(hours > 0) {
                val hoursStr = (if (hours < 10) "0" else "") + hours
                //Log.d("test conversion h", hoursStr)
                toReturn = hoursStr + ":" + toReturn
            }

            return toReturn
        }

        //Format accepté : hh:mm:ss ou mm:ss
        fun fromTimeStringToSec(timeString:String) : Int{
            val parts = timeString.split(":")

            //Format incorrect
            if(parts.size > 3) throw IncorrectTimeTextException()

            var position = 1 //Position in the foreach, démarre à 1, utilisée pour le pow

            var toReturn = 0.0

            parts.forEach {
                var mult : Double = 60.0
                //Nous multiplions les heures par 3600 et les minutes par 60
                mult = mult.pow(parts.size - position)

                val timeInSecond = it.toInt() * mult

                toReturn+=timeInSecond

                position++
            }

            return toReturn.toInt()
        }
    }
}

class IncorrectTimeTextException : Exception() {}