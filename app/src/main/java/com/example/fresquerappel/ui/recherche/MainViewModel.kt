package com.example.fresquerappel.ui.recherche

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

        fun getInstance(owner: ViewModelStoreOwner): MainViewModel? {
            if (instance == null) {
                synchronized(MainViewModel()::class.java) {
                    if (instance == null) {
                        instance = ViewModelProvider(owner).get(
                            MainViewModel::class.java)
                    }
                }
            }
            return instance
        }
    }



    private val _name = MutableLiveData<String>()
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

        _carte1.apply {  value = carte1 }
        _carte2.value = carte2
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
    }

    fun changeCollage(name:String){
        //Log.d("adr1", "nous changeons fresque" + name)
        _name.value = name
    }
}