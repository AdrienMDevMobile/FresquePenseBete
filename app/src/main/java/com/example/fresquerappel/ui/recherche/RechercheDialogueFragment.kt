package com.example.fresquerappel.ui.recherche

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.example.fresquerappel.R
import kotlinx.android.synthetic.main.dialogue_fragment_recherche.*


class RechercheDialogueFragment : DialogFragment() {

    companion object{
        val maxLengthNumber = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //here fragment_my_dialog is the UI of Custom Dialog

        return inflater.inflate(R.layout.dialogue_fragment_recherche, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ETcarte1.addTextChangedListener(TextWatcherGoTo(ETcarte2))

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
            //if(ETcarte1.text.length)
            if(s?.length!! >= maxLengthNumber) {
                //Log.d("test", "2")
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


    class TextWatcherStartSearch : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            Log.d("test", "Nous sommes APRES le textChanged")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            Log.d("test", "Nous sommes AVANT le textChanged")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //if(ETcarte1.text.length)
            Log.d("test", "Nous sommes dans le textChanged")
        }
    }

}