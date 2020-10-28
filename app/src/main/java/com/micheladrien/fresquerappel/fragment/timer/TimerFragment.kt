package com.micheladrien.fresquerappel.fragment.timer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.fresquerappel.R


class TimerFragment : Fragment() {

    private lateinit var timerViewModel: TimerViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TimerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_timer, container, false)

        val arrayList = ArrayList<String>()
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data

        /**
        //TODO https://developer.android.com/guide/topics/ui/layout/recyclerview
        //viewManager = LinearLayoutManager(this)
        viewAdapter = TimerAdapter()

        recyclerView = root.findViewById<RecyclerView>(R.id.list_timer).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            //layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        /* TODO Utilisation VM : quand j'aurai mis en place la vue.
        TODO RecyclerView.Adapter.notify…() quand un element est changé
        val adapter = ArrayAdapter<String>(activity as Context, R.layout., arrayList)
        Log.d("ami","avant setadapter")
        val list_timer: ListView = root.findViewById(R.id.list_timer)
        list_timer.adapter = adapter
        Log.d("ami","Apres set adapter")

        timerViewModel.timerCollection.observe(viewLifecycleOwner, Observer {
            Log.d("ami","Nous observons")
            adapter.clear()
            for (timer in it) {
                adapter.add(timer)
            }
            Log.d("ami","fin d'observation")
        })
        Log.d("ami","fin set up")
        */
        **/
        return root
    }

}