package com.micheladrien.fresquerappel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.ui.recherche.MainViewModel
import kotlinx.android.synthetic.main.fragment_relation.*

class RelationFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_relation, container, false)

        /* Set up des observeurs debut */
        //Explication
        val textView: TextView = root.findViewById(R.id.text_explication)
        mainViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //Id des deux cartes
        mainViewModel.carte1.observe(viewLifecycleOwner, Observer {
            txt_id_carte1.text = it.toString()
        })
        mainViewModel.carte2.observe(viewLifecycleOwner, Observer {
            txt_id_carte2.text = it.toString()
        })
        //Relation = -> <- X, etc + sa couleur + optionel/obligatoire
        mainViewModel.relation.observe(viewLifecycleOwner, Observer {
            txt_relation.text = it
        })
        mainViewModel.relation_color.observe(viewLifecycleOwner, Observer {
            txt_relation.setTextColor(it)
        })
        mainViewModel.relation_mandatory.observe(viewLifecycleOwner, Observer {
            txt_mandatory.text = it
        })
        /* Set up des observeurs debut */

        return root
    }

}