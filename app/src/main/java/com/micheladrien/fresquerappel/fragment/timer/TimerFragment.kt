package com.micheladrien.fresquerappel.fragment.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.fragment.relation.RelationViewModel

class TimerFragment : Fragment() {/*

    private lateinit var timerViewModel: TimerViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TimerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_timer, container, false)

        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        //obtain a handle to the RecycleView object, connect it to a layout manager, and attach an adapter for the data to be displayed:
        //viewManager = LinearLayoutManager(context)


        /*
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        val timer_set = arrayOf("one", "two")
        //val timer_set = arrayOf()
        // and the array that contains the data
        viewAdapter = TimerAdapter(timer_set) */

        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_timer).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            this.setHasFixedSize(true)
        }

        /* TODO Utilisation VM : quand j'aurai mis en place la vue.
        TODO RecyclerView.Adapter.notify…() quand un element est changé */

        timerViewModel.timerLiveData.observe(viewLifecycleOwner, Observer{

            //Dans l'observe : ecrase et refait le RecyclerViewAdapter
            viewAdapter = TimerAdapter(it)
            // use a linear layout manager
            recyclerView.layoutManager = LinearLayoutManager(context)
            // specify an viewAdapter (see also next example)
            recyclerView.adapter = viewAdapter

        })
        //Log.d("ami","fin set up")


        //TODO Donner la fonction delete de ma VM dans le bouton X (a faire dans l'adapter ?) Il me faut probablement creer une classe custom OnClickListener

        return root
    }*/

}

