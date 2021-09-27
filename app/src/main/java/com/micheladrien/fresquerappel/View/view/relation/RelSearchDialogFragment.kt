package com.micheladrien.fresquerappel.View.view.relation

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
import com.micheladrien.fresquerappel.databinding.DialogueFragmentRechercheBinding


class RelSearchDialogFragment : DialogFragment() {

    companion object{
        val KEY_CARD1 = "card1"
        val KEY_CARD2 = "card2"
        val KEY_RESULT = "ResRel"
        private val maxLengthNumber = 2
    }

    private var _binding: DialogueFragmentRechercheBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //relationViewModel = ViewModelProvider(requireActivity()).get(RelationViewModel::class.java)

        _binding = DialogueFragmentRechercheBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun startSearch():Boolean{

        if(checkNumbers()){
            val card1 = binding.ETcard1.text.toString().toInt()
            val card2 = binding.ETcard2.text.toString().toInt()
            /* Not sharing a Viewmodel with relationFragment anymore. Returns value to TargetFragment
            relationViewModel.changeCards(card1, card2)
            relationViewModel.research() */
            val bundle = Bundle()
            bundle.putInt(KEY_CARD1, card1)
            bundle.putInt(KEY_CARD2, card2)
            parentFragmentManager.setFragmentResult(KEY_RESULT, bundle)

            return true
        }
        else
            return false

    }



    //Vérifie que les numéros de cartes ont bien étés entrés
    fun checkNumbers():Boolean{
        var to_return = true

        if (TextUtils.isEmpty(binding.ETcard2.text.toString())) {
            binding.ETcard2.error = getString(R.string.warning_missing_card_num)
            binding.ETcard2.requestFocus()
            to_return = false
        }
        if (TextUtils.isEmpty(binding.ETcard1.text.toString())) {
            binding.ETcard1.error = getString(R.string.warning_missing_card_num)
            binding.ETcard1.requestFocus()
            to_return = false
        }

        return to_return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Nous definissons le ViewModel
        //relationViewModel = ViewModelProvider(this).get(RelationViewModel::class.java)

        binding.ETcard1.addTextChangedListener(TextWatcherGoTo(binding.ETcard2))
        binding.ETcard2.addTextChangedListener(TextWatcherStartSearch(this))
        binding.BTNrecherche.setOnClickListener { if(startSearch()) this.dismiss() }
        /*
         Actuellement mis en commentaire : problème de focus pour l'apparition du clavier :
         si je ne force pas le focus : rien ne se passe
         si je force le fos : le clavier ne peut pas disparaitre quand l'utilisateur quitte la memu pop-up

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


        //récupère le focus
        //ETcarte1.requestFocus()
        */
    }


    //Text watchers : actions au moment où l'utilisateur rentre le texte. Passe la main à l'edittext suivant au moment où la taille maximale est atteinte
    //Premier editText : passe à l'edit text suivant
    class TextWatcherGoTo(val editTextGoTo: EditText) : TextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s?.length!! >= maxLengthNumber) {
                editTextGoTo.requestFocus()
            }
        }

        //Fonction non utilisées
        override fun afterTextChanged(s: Editable?) {

        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
    }

    //Second edit text : lance la recherche.
    class TextWatcherStartSearch(val relSearchDialogFragment: RelSearchDialogFragment) : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s?.length!! >= maxLengthNumber) {
                if(relSearchDialogFragment.startSearch())
                    relSearchDialogFragment.dismiss()
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }


    }

}
