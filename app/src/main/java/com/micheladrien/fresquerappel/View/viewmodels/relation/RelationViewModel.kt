package com.micheladrien.fresquerappel.View.viewmodels.relation

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.micheladrien.fresquerappel.Data.managers.CollageDataManager
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.Data.datas.Relation
import com.micheladrien.fresquerappel.Data.datas.RelationDirection
import com.micheladrien.fresquerappel.Data.datas.RelationMandatory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
Le Viewmodel sera partagé entre l'activité main (reçoit le nom de la fresque),
le fragments de recherche (reçoit numéro des cartes)
le fragments principal (affiche les informations)
 */


//ou récupérer valeur par défaut (mm fonction) au moment de la recherche si val null.
@HiltViewModel
class RelationViewModel @Inject constructor (val collageDataManager: CollageDataManager, application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = application.getString(R.string.please_start_search)
    }
    private val _card1 = MutableLiveData<Int>()
    private val _card2 = MutableLiveData<Int>()
    private val _relation = MutableLiveData<String>()
    private val _relation_color = MutableLiveData<Int>()
    private val _relation_mandatory = MutableLiveData<String>()

    val text: LiveData<String> = _text
    val card1 : LiveData<Int> = _card1
    val card2 : LiveData<Int> = _card2
    val relation : LiveData<String> = _relation
    val relation_color : LiveData<Int> = _relation_color
    val relation_mandatory : LiveData<String> = _relation_mandatory

    fun changeCards(card1:Int, card2:Int){

        if(card1 >= card2){
            _card1.value = card2
            _card2.value = card1
        }
        else {
            _card1.value = card1
            _card2.value = card2
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
        if(_card1.value != null && _card2.value != null){
            val relationModel = collageDataManager.researchRelation(_card1.value!!, _card2.value!!)

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