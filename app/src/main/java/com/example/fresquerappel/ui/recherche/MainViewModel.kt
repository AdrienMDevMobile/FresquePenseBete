package com.example.fresquerappel.ui.recherche

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.*


/*
Le Viewmodel sera partagé entre l'activité main (reçoit le nom de la fresque),
le fragment de recherche (reçoit numéro des cartes)
le fragment principal (affiche les informations)
L'appel de l'activité et le fragment se font par xml.
Je dois donc le faire en Singleton
 */



//TODO : Le nom de la fresque n'est pas définie par défaut. Comment gérer cela ? La définir par défaut avec getString(R)
//ou récupérer valeur par défaut (mm fonction) au moment de la recherche si val null.
class MainViewModel : ViewModel() {

    private fun MainViewModel() {}
    companion object {
        private var  instance: MainViewModel? = null

        fun getInstance(owner: ViewModelStoreOwner, context: Context): MainViewModel? {
            if (instance == null) {
                synchronized(MainViewModel()::class.java) {
                    if (instance == null) {
                        instance = ViewModelProvider(owner).get(
                            MainViewModel::class.java)
                        RelationModel.initialize(context)
                    }
                }
            }
            return instance
        }
    }



    private val _name = MutableLiveData<String>().apply {
       value = "Climat"
    }
    private val _text = MutableLiveData<String>().apply {
        value = "Explication"
    }
    private val _carte1 = MutableLiveData<Int>()
    private val _carte2 = MutableLiveData<Int>()
    private val _relation = MutableLiveData<RelationDirection>()

    val name : LiveData<String> = _name
    val text: LiveData<String> = _text
    val carte1 : LiveData<Int> = _carte1
    val carte2 : LiveData<Int> = _carte2
    val relation : MutableLiveData<RelationDirection> = _relation

    fun changeCards(carte1:Int, carte2:Int){

        if(carte1 >= carte2){
            _carte1.value = carte2
            _carte2.value = carte1
        }
        else {
            _carte1.value = carte1
            _carte2.value = carte2
        }


    }

    fun research(){
        if(_carte1.value != null && _carte2.value != null){
            if(_name.value == null){
                _name.value = "Climat"
            }
            val relationModel = RelationModel.research(_carte1.value!!, _carte2.value!!, _name.value!!)

            _relation.apply { value =  relationModel.relationDirection }

            _text.apply{ value  = relationModel.explanation }

        }
        else {Log.d("recherche","recherche non lancee")}
    }

    fun changeCollage(name:String){
        _name.value = name
    }
}