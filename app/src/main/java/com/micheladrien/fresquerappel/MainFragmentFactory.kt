package com.micheladrien.fresquerappel

import android.util.Log
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.micheladrien.fresquerappel.fragment.about.AboutFragment
import com.micheladrien.fresquerappel.fragment.notes.NotesFragment
import com.micheladrien.fresquerappel.fragment.relation.RelationFragment
import com.micheladrien.fresquerappel.fragment.single.SingleCardFragment
import com.micheladrien.fresquerappel.fragment.timer.TimerFragment

class MainFragmentFactory: FragmentFactory() {
    //Variables


    //Put arguments here so they are given to the variables defined above

    @NonNull
    override fun instantiate(@NonNull classLoader : ClassLoader, @NonNull className : String)  : Fragment {

        val clazz : Class<out Fragment> = loadFragmentClass(classLoader, className)
        when (clazz) {
            RelationFragment::class.java -> return RelationFragment()
            TimerFragment::class.java-> return TimerFragment()
            SingleCardFragment::class.java -> return SingleCardFragment()
            NotesFragment::class.java -> return NotesFragment()
            AboutFragment::class.java -> return AboutFragment()

            else -> return super.instantiate(classLoader, className)
        }
    }



}