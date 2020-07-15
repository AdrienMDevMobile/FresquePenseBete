package com.example.fresquerappel.ui.recherche

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.fresquerappel.R
import kotlinx.android.synthetic.main.dialogue_fragment_recherche.*


class RechercheDialogueFragment : DialogFragment() {

    class rechTextWatcher : TextWatcher {
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
        //récupère le focus
        ETcarte1.requestFocus()

        //Set le focus. Je dois passer par l'activité pour pouvoir donné la string du Systeme service demandé
        //Système service = service présent de base dans Android
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        Log.d("test", "b4")

        ETcarte1.addTextChangedListener(rechTextWatcher())

    }
}