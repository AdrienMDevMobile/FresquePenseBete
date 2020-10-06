package com.micheladrien.fresquerappel.ui.recherche

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.DataManager
import com.micheladrien.fresquerappel.R
/*
Le Viewmodel sera partagé entre l'activité main (reçoit le nom de la fresque),
le fragment de recherche (reçoit numéro des cartes)
le fragment principal (affiche les informations)
 */


//ou récupérer valeur par défaut (mm fonction) au moment de la recherche si val null.
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _name = MutableLiveData<String>().apply {
       value = application.getString(R.string.collage_climat)
    }
    private val _text = MutableLiveData<String>().apply {
        value = "Explication"
    }
    private val _carte1 = MutableLiveData<Int>()
    private val _carte2 = MutableLiveData<Int>()
    private val _relation = MutableLiveData<String>()
    private val _relation_color = MutableLiveData<Int>()
    private val _relation_mandatory = MutableLiveData<String>()

    val name : LiveData<String> = _name
    val text: LiveData<String> = _text
    val carte1 : LiveData<Int> = _carte1
    val carte2 : LiveData<Int> = _carte2
    val relation : LiveData<String> = _relation
    val relation_color : LiveData<Int> = _relation_color
    val relation_mandatory : LiveData<String> = _relation_mandatory

    private val dataManager:DataManager = RelationDataManager()

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

    @SuppressLint("DefaultLocale")
    fun research(){
        if(!dataManager.isInitalised()) {
            dataManager.initialize(getApplication(), this.name.value.toString().toLowerCase())
        }
        Log.d("0708", "nous avons inialisé")
        if(_carte1.value != null && _carte2.value != null){
            if(_name.value == null){
                _name.value = "Climat"
            }
            val relationModel = dataManager.research(_carte1.value!!, _carte2.value!!, _name.value!!)

            _text.apply{ value  = relationModel.explanation }
            drawRelation(relationModel.relation)

        }
        else {Log.d("recherche","recherche non lancee")}


    }

    /* 1.3 : Retour arrière
    fun changeCollage(name:String){
        //Liste des fresques non supportés
        when(name){
            getApplication<Application>().getString(R.string.menu_expert), getApplication<Application>().getString(R.string.menu_numerique), getApplication<Application>().getString(R.string.menu_oceanne) -> {
                Toast.makeText(getApplication(), getApplication<Application>().getString(R.string.toast_collage_unavailable, name), Toast.LENGTH_SHORT).show()
                return
            }
        }
        _name.value = name
        dataManager.initialize(getApplication(), name.toLowerCase())
    } */

    private fun drawRelation(relation : Relation){
        if(relation.direction == RelationDirection.UP || relation.direction == RelationDirection.DOWN
            || relation.direction == RelationDirection.UPDOWN){
            if(relation.mandatory == RelationMandatory.MANDATORY){

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    _relation_color.value =  getApplication<Application>().getColor( R.color.green_correct_mandatory)
                _relation_mandatory.value = getApplication<Application>().getString(R.string.relation_mandatory)
            }

            else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    _relation_color.value =  getApplication<Application>().getColor( R.color.green_correct_optional)
                _relation_mandatory.value =  getApplication<Application>().getString(R.string.relation_optional)
            }

        }
        else {
            _relation_mandatory.value = ""
        }
        if(relation.direction == RelationDirection.UP){
            _relation.value =  getApplication<Application>().getString(R.string.relation_up)
        }

        if(relation.direction == RelationDirection.DOWN){
            _relation.value =  getApplication<Application>().getString(R.string.relation_down)
        }

        if(relation.direction == RelationDirection.UPDOWN){
            _relation.value =  getApplication<Application>().getString(R.string.relation_updown)
        }

        if(relation.direction == RelationDirection.INCORRECT){
            _relation.value =   getApplication<Application>().getString(R.string.relation_incorrect)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                _relation_color.value =  getApplication<Application>().getColor( R.color.red_incorrect)
        }


        if(relation.direction == RelationDirection.NONE){
            _relation.value =  getApplication<Application>().getString(R.string.relation_none)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                _relation_color.value =  getApplication<Application>().getColor( R.color.gray_missing)
        }
    }
}