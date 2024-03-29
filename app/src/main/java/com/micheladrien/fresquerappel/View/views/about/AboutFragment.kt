package com.micheladrien.fresquerappel.View.views.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.micheladrien.fresquerappel.R

class AboutFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_about, container, false)
        return root
    }

    override fun onStart() {
        super.onStart()
        activity?.setTitle(getString(R.string.menu_about))
    }
}