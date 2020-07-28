package com.example.fresquerappel.ui.recherche

import android.app.Application
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.example.fresquerappel.R
import kotlinx.android.synthetic.main.fragment_main.*


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
        private var context : Context? = null

        fun getInstance(owner: ViewModelStoreOwner, context: Context): MainViewModel? {
            if (instance == null) {
                synchronized(MainViewModel()::class.java) {
                    if (instance == null) {
                        instance = ViewModelProvider(owner).get(
                            MainViewModel::class.java)
                        this.context = context
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
    private val _relation = MutableLiveData<String>()
    private val _relation_color = MutableLiveData<Int>()
    private val _relation_mandatory = MutableLiveData<String>()

    val name : LiveData<String> = _name
    val text: LiveData<String> = _text
    val carte1 : LiveData<Int> = _carte1
    val carte2 : LiveData<Int> = _carte2
    val relation : MutableLiveData<String> = _relation
    val relation_color = _relation_color
    val relation_mandatory = _relation_mandatory

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

            _text.apply{ value  = relationModel.explanation }
            drawRelation(relationModel.relation)

        }
        else {Log.d("recherche","recherche non lancee")}
    }

    fun changeCollage(name:String){
        _name.value = name
    }

    private fun drawRelation(relation : Relation){
        if(relation.direction == RelationDirection.UP || relation.direction == RelationDirection.DOWN
            || relation.direction == RelationDirection.UPDOWN){
            if(relation.mandatory == RelationMandatory.MANDATORY){
                context?.let { ContextCompat.getColor(it, R.color.green_correct_mandatory) }?.let {
                    _relation_color.value = it
                }
                _relation_mandatory.value = context!!.getString(R.string.relation_mandatory)
            }

            else {
                context?.let { ContextCompat.getColor(it, R.color.green_correct_optional) }?.let {
                    _relation_color.value = it
                }
                _relation_mandatory.value = context!!.getString(R.string.relation_mandatory)
            }

        }
        else {
            _relation_mandatory.value = ""
        }
        if(relation.direction == RelationDirection.UP){
            _relation.value = context?.getString(R.string.relation_up)
        }

        if(relation.direction == RelationDirection.DOWN){
            _relation.value = context?.getString(R.string.relation_down)
        }

        if(relation.direction == RelationDirection.UPDOWN){
            _relation.value = context?.getString(R.string.relation_updown)
        }

        if(relation.direction == RelationDirection.INCORRECT){
            _relation.value =  context?.getString(R.string.relation_incorrect)
            context?.let { ContextCompat.getColor(it, R.color.red_incorrect) }?.let {
                _relation_color.value = it
            }
        }


        if(relation.direction == RelationDirection.NONE){
            _relation.value =  context?.getString(R.string.relation_none)
            context?.let { ContextCompat.getColor(it, R.color.gray_missing) }?.let {
                _relation_color.value = it
            }
        }

    }
}