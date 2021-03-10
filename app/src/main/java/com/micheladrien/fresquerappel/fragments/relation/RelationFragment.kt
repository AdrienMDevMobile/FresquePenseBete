package com.micheladrien.fresquerappel.fragments.relation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.micheladrien.fresquerappel.databinding.FragmentRelationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RelationFragment : Fragment() {

    private var _binding: FragmentRelationBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val relationViewModel: RelationViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        //Vue que ce ViewModel recevra des infos du fragments pop up
        //je suis obligé de mettre l'activité main comme propriétaire
        //relationViewModel = ViewModelProvider(requireActivity()).get(RelationViewModel::class.java)

        _binding = FragmentRelationBinding.inflate(inflater, container, false)
        val root = binding.root


        /* Set up des observeurs debut */
        //Explication
        val textView: TextView = binding.textExplanation
        relationViewModel.text.observe(viewLifecycleOwner, /* Observer */  {
            textView.text = it
        })

        //Id des deux cartes
        relationViewModel.card1.observe(viewLifecycleOwner, {
            binding.txtIdCard1.text = it.toString()
        })
        relationViewModel.card2.observe(viewLifecycleOwner, {
            binding.txtIdCard2.text = it.toString()
        })
        //Relation = -> <- X, etc + sa couleur + optionel/obligatoire
        relationViewModel.relation.observe(viewLifecycleOwner, {
            binding.txtRelation.text = it
        })
        relationViewModel.relation_color.observe(viewLifecycleOwner, {
            binding.txtRelation.setTextColor(it)
        })
        relationViewModel.relation_mandatory.observe(viewLifecycleOwner, {
            binding.txtMandatory.text = it
        })
        /* Set up des observeurs debut */

        /* Défini le bouton de recherche -- Debut */
        val fab: FloatingActionButton = binding.relationSearchButton

        fab.setOnClickListener { view ->
            val dialogFragment = RelSearchDialogFragment()
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
            requireActivity().supportFragmentManager
                    .setFragmentResultListener(RelSearchDialogFragment.KEY_RESULT, viewLifecycleOwner,
                            { requestKey, result -> relationViewModel.changeCards(
                                    result.getInt(RelSearchDialogFragment.KEY_CARD1), result.getInt(RelSearchDialogFragment.KEY_CARD2))
                                relationViewModel.research()
                            })
        }
        /* Défini le bouton de recherche -- Fin */

        return root
    }

}