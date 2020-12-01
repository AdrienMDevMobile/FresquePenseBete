package com.micheladrien.fresquerappel.fragment.relation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.databinding.FragmentRelationBinding

class RelationFragment : Fragment() {

    private lateinit var relationViewModel: RelationViewModel

    private var _binding: FragmentRelationBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Vue que ce ViewModel recevra des infos du fragment pop up
        //je suis obligé de mettre l'activité main comme propriétaire
        relationViewModel = ViewModelProvider(requireActivity()).get(RelationViewModel::class.java)

        _binding = FragmentRelationBinding.inflate(inflater, container, false)
        val root = binding.root


        /* Set up des observeurs debut */
        //Explication
        val textView: TextView = binding.textExplication
        relationViewModel.text.observe(viewLifecycleOwner, /* Observer */  {
            textView.text = it
        })

        //Id des deux cartes
        relationViewModel.carte1.observe(viewLifecycleOwner,  {
            binding.txtIdCarte1.text = it.toString()
        })
        relationViewModel.carte2.observe(viewLifecycleOwner,  {
            binding.txtIdCarte2.text = it.toString()
        })
        //Relation = -> <- X, etc + sa couleur + optionel/obligatoire
        relationViewModel.relation.observe(viewLifecycleOwner,  {
            binding.txtRelation.text = it
        })
        relationViewModel.relation_color.observe(viewLifecycleOwner,  {
            binding.txtRelation.setTextColor(it)
        })
        relationViewModel.relation_mandatory.observe(viewLifecycleOwner,  {
            binding.txtMandatory.text = it
        })
        /* Set up des observeurs debut */

        /* Défini le bouton de recherche -- Debut */
        val fab: FloatingActionButton = binding.relationSearchButton

        fab.setOnClickListener { view ->
            val dialogFragment = RelationRechercheDialogueFragment()
            val bundle = Bundle()
            bundle.putBoolean("notAlertDialog", true)
            dialogFragment.arguments = bundle
            val ft = activity?.supportFragmentManager?.beginTransaction()
            val prev = activity?.supportFragmentManager?.findFragmentByTag("dialog")
            if (prev != null)
            {
                ft?.remove(prev)
            }
            ft?.addToBackStack(null)
            if (ft != null) {
                dialogFragment.show(ft, "dialog")
            }

        }
        /* Défini le bouton de recherche -- Fin */

        return root
    }

}