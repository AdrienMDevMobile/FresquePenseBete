package com.micheladrien.fresquerappel.View.view.single

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.View.viewmodel.single.SingleViewModel
import com.micheladrien.fresquerappel.databinding.FragmentSingleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleCardFragment : Fragment() {

    private var _binding: FragmentSingleBinding? = null
    private val binding get() = _binding!!

    private val relationViewModel: SingleViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingleBinding.inflate(inflater, container, false)
        val root = binding.root

        return root
    }

    override fun onStart() {
        super.onStart()
        activity?.setTitle(getString(R.string.menu_single))
    }
}