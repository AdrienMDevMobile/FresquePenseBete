package com.micheladrien.fresquerappel.ui.recherche

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.micheladrien.fresquerappel.R
import kotlinx.android.synthetic.main.dialogue_fragment_recherche.*


class RechercheDialogueFragment : DialogFragment() {

    companion object{
        val maxLengthNumber = 2
    }
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //here fragment_my_dialog is the UI of Custom Dialog

        return inflater.inflate(R.layout.dialogue_fragment_recherche, container, false)
    }

    fun startSearch():Boolean{

        if(checkNumbers()){
            val carte1 = ETcarte1.text.toString().toInt()
            val carte2 = ETcarte2.text.toString().toInt()
            mainViewModel.changeCards(carte1, carte2)
            mainViewModel.research()

            return true
        }
        else
            return false

    }

    //Vérifie que les numéros de cartes ont bien étés entrés
    fun checkNumbers():Boolean{
        if (TextUtils.isEmpty(ETcarte1.text.toString())) {
            ETcarte1.error = getString(R.string.warning_missing_carte_num)
            return false
        }
        if (TextUtils.isEmpty(ETcarte2.text.toString())) {
            ETcarte2.error = getString(R.string.warning_missing_carte_num)
            return false
        }

        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Nous definissons le ViewModel
        mainViewModel = context?.let { MainViewModel.getInstance(this, it) }!!
            //ViewModelProvider(this).get(MainViewModel::class.java)

        ETcarte1.addTextChangedListener(TextWatcherGoTo(ETcarte2))
        ETcarte2.addTextChangedListener(TextWatcherStartSearch(this))
        BTNrecherche.setOnClickListener {
            if(startSearch())
                this.dismiss() }
        /*
        ETcarte1.setFocusable(true)
        ETcarte1.setFocusableInTouchMode(true)

        ETcarte2.setFocusable(true)
        ETcarte2.setFocusableInTouchMode(true)


        //Set le focus. Je dois passer par l'activité pour pouvoir donné la string du Systeme service demandé
        //Système service = service présent de base dans Android
        /*val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY) */



        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        //imm?.showSoftInput(ETcarte1, InputMethodManager.SHOW_IMPLICIT)
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        //Log.d("test", "b4")


        //récupère le focus
        //ETcarte1.requestFocus()
        */
    }

    /*
    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        Log.d("test", "cancel")

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        //imm?.hideSoftInputFromWindow(view?.windowToken, 0)
        //imm?.hideSoftInputFromWindow(view?.windowToken, 0)
        imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, InputMethodManager.HIDE_IMPLICIT_ONLY)
        imm?.hideSoftInputFromWindow(view?.windowToken, 1)
    }
*/

    /*
    override fun onDestroyView() {


        Log.d("test", "destroy")

        //val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        //imm?.hideSoftInputFromWindow(view?.windowToken, 0)
        //imm?.hideSoftInputFromWindow(view?.windowToken, 0)
        //imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, InputMethodManager.HIDE_IMPLICIT_ONLY);

        super.onDestroyView()
    }
*/

    //Text watchers : actions au moment où l'utilisateur rentre le texte. Passe la main à l'edittext suivant au moment où la taille maximale est atteinte
    class TextWatcherGoTo(val editTextGoTo: EditText) : TextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s?.length!! >= maxLengthNumber) {
                editTextGoTo.requestFocus()
            }
        }

        //Fonction non utilisées
        override fun afterTextChanged(s: Editable?) {
            //Log.d("test", "Nous sommes APRES le textChanged "+ s?.length + " " + s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //Log.d("test", "Nous sommes AVANT le textChanged" + s.toString() + start + " " + count + " " + after)
        }
    }


    class TextWatcherStartSearch(val rechercheDialogueFragment: RechercheDialogueFragment) : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s?.length!! >= maxLengthNumber) {
                if(rechercheDialogueFragment.startSearch())
                    rechercheDialogueFragment.dismiss()
            }
        }

        override fun afterTextChanged(s: Editable?) {
            //Log.d("test", "Nous sommes APRES le textChanged")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //Log.d("test", "Nous sommes AVANT le textChanged")
        }


    }

}