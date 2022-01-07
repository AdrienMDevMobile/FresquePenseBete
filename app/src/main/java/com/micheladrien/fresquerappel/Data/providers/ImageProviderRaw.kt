package com.micheladrien.fresquerappel.Data.providers

class ImageProviderRaw : ImageProvider {

    override fun getCardImage(number: Int) {
        TODO("Not yet implemented")
    }

    fun getCardNameLocation(number: Int) : String{
        return  "cardsImage/fr_adulte_carte_${number}_recto.png"
    }
}