package com.micheladrien.fresquerappel.fragment.relation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.micheladrien.fresquerappel.R
import kotlinx.android.synthetic.main.fragment_relation.*

class RelationFragment : Fragment() {

    private lateinit var relationViewModel: RelationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Vue que ce ViewModel recevra des infos du fragment pop up
        //je suis obligé de mettre l'activité main comme propriétaire
        relationViewModel = ViewModelProvider(requireActivity()).get(RelationViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_relation, container, false)

        /* Set up des observeurs debut */
        //Explication
        val textView: TextView = root.findViewById(R.id.text_explication)
        relationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //Id des deux cartes
        relationViewModel.carte1.observe(viewLifecycleOwner, Observer {
            txt_id_carte1.text = it.toString()
        })
        relationViewModel.carte2.observe(viewLifecycleOwner, Observer {
            txt_id_carte2.text = it.toString()
        })
        //Relation = -> <- X, etc + sa couleur + optionel/obligatoire
        relationViewModel.relation.observe(viewLifecycleOwner, Observer {
            txt_relation.text = it
        })
        relationViewModel.relation_color.observe(viewLifecycleOwner, Observer {
            txt_relation.setTextColor(it)
        })
        relationViewModel.relation_mandatory.observe(viewLifecycleOwner, Observer {
            txt_mandatory.text = it
        })
        /* Set up des observeurs debut */

        /* Défini le bouton de recherche -- Debut */
        val fab: FloatingActionButton = root.findViewById(R.id.relation_search_button)

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