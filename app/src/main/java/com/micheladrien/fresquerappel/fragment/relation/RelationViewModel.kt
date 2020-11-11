package com.micheladrien.fresquerappel.fragment.relation

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.manager.DataManager
import com.micheladrien.fresquerappel.manager.MainDataManager
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.Relation
import com.micheladrien.fresquerappel.datas.RelationDirection
import com.micheladrien.fresquerappel.datas.RelationMandatory
import com.micheladrien.fresquerappel.tools.JsonReader

/*
Le Viewmodel sera partagé entre l'activité main (reçoit le nom de la fresque),
le fragment de recherche (reçoit numéro des cartes)
le fragment principal (affiche les informations)
 */


//ou récupérer valeur par défaut (mm fonction) au moment de la recherche si val null.
class RelationViewModel(application: Application) : AndroidViewModel(application) {

    /* ancien MainViewmodel est désormais RelationView model. Name se trouve dans le nouveau ViewModel
    private val _name = MutableLiveData<String>().apply {
       value = application.getString(R.string.collage_climat)
    } */
    private val _text = MutableLiveData<String>().apply {
        value = "Explication"
    }
    private val _carte1 = MutableLiveData<Int>()
    private val _carte2 = MutableLiveData<Int>()
    private val _relation = MutableLiveData<String>()
    private val _relation_color = MutableLiveData<Int>()
    private val _relation_mandatory = MutableLiveData<String>()

    //val name : LiveData<String> = _name
    val text: LiveData<String> = _text
    val carte1 : LiveData<Int> = _carte1
    val carte2 : LiveData<Int> = _carte2
    val relation : LiveData<String> = _relation
    val relation_color : LiveData<Int> = _relation_color
    val relation_mandatory : LiveData<String> = _relation_mandatory

    private val dataManager: DataManager = MainDataManager(getApplication())

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

        /* Actuellement, l'initialisation se fait automatiquement dans la classe qui porte le singleton
        if(!dataManager.isDataInitialised()) {
            dataManager.loadData( getApplication<Application>().getString(R.string.collage_climat))
        }
        */
        //Log.d("0708", "nous avons inialisé")
        if(_carte1.value != null && _carte2.value != null){
            val relationModel = dataManager.researchRelation(_carte1.value!!, _carte2.value!!)

            _text.apply{ value  = relationModel.explanation }
            drawRelation(relationModel.relation)

        }
        else {Log.d("recherche","recherche non lancee")}

    }

    //1.3 : Change collage déplacé dans settings Retour arrière

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