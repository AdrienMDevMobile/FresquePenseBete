package com.micheladrien.fresquerappel.View.views.single

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.micheladrien.fresquerappel.Data.providers.ImageProvider
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.View.viewmodels.single.SingleViewModel
import com.micheladrien.fresquerappel.View.viewmodels.single.SingleViewModelImpl
import com.micheladrien.fresquerappel.databinding.FragmentSingleBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream
import javax.inject.Inject


@AndroidEntryPoint
class SingleCardFragment : Fragment() {

    private var _binding: FragmentSingleBinding? = null
    private val binding get() = _binding!!

    private val singleViewModel: SingleViewModel by viewModels<SingleViewModelImpl>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingleBinding.inflate(inflater, container, false)
        val root = binding.root




        singleViewModel.cardNumber.observe(viewLifecycleOwner, {
            binding.cardNumberET.setText(it.toString())
        })

        singleViewModel.cardName.observe(viewLifecycleOwner, {
            binding.cardNameTV.setText(it)
        })

        singleViewModel.explanation.observe(viewLifecycleOwner, {
            binding.cardTextTV.setText(it)
        })

        singleViewModel.setInt.observe(viewLifecycleOwner, {
            binding.setNumberTV.setText(it.toString())
        })

        singleViewModel.cardImage.observe(viewLifecycleOwner, {
            binding.imageCard.setImageBitmap(it)
        })


            return root
        }

    override fun onStart() {
        super.onStart()
        activity?.setTitle(getString(R.string.menu_single))
    }
}