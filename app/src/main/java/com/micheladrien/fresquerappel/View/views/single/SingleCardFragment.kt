package com.micheladrien.fresquerappel.View.views.single

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.View.viewmodels.single.SingleViewModel
import com.micheladrien.fresquerappel.View.viewmodels.single.SingleViewModelImpl
import com.micheladrien.fresquerappel.databinding.FragmentSingleBinding
import dagger.hilt.android.AndroidEntryPoint


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

        binding.nextCardBtn.setOnClickListener {
            singleViewModel.goToNextCard()
        }
        binding.prevCardBtn.setOnClickListener {
            singleViewModel.goToPreviousCard()
        }

        binding.cardNumberET.setOnClickListener {
            binding.cardNumberET.setText("")
        }

        binding.cardNumberET.setOnEditorActionListener(object : TextView.OnEditorActionListener {

            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

                v?.text.toString().toInt().let {
                    singleViewModel.goToCard(it)
                }

                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v?.windowToken, 0)

                return true
            }
        })


        return root
    }

    override fun onStart() {
        super.onStart()
        activity?.setTitle(getString(R.string.menu_single))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}