package com.micheladrien.fresquerappel.fragments.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.databinding.FragmentTimerBinding
import com.micheladrien.fresquerappel.tools.TimerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private val timerViewModel: TimerViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TimerAdapter

    private var _binding: FragmentTimerBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root = binding.root

        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_timer).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            this.setHasFixedSize(true)
        }

        timerViewModel.timerLiveData.observe(viewLifecycleOwner, {

            //Dans l'observe : ecrase et refait le RecyclerViewAdapter
            viewAdapter = TimerAdapter(it)
            // use a linear layout manager
            recyclerView.layoutManager = LinearLayoutManager(context)
            // specify an viewAdapter (see also next example)
            recyclerView.adapter = viewAdapter

        })
        //Log.d("ami","fin set up")

        timerViewModel.timerState.observe(viewLifecycleOwner, {
            when(it){
                TimerState.STARTED ->  {
                    val toast = Toast.makeText(context, context?.getString(R.string.timer_set_toast), Toast.LENGTH_SHORT)
                    toast.show()
                }
                TimerState.STOPPED -> {
                    val toast = Toast.makeText(context, context?.getString(R.string.timer_stop_toast), Toast.LENGTH_SHORT)
                    toast.show()
                }
                TimerState.ERROR -> {
                    val toast = Toast.makeText(context, context?.getString(R.string.timer_error_toast), Toast.LENGTH_SHORT)
                    toast.show()
                }

            }
        })


        //TODO Donner la fonction delete de ma VM dans le bouton X (a faire dans l'adapter ?) Il me faut probablement creer une classe custom OnClickListener

        //Bouton lancer les timers
        binding.BTNStartTimer.setOnClickListener {
            //context?.startService(Intent(context, TimerService::class.java))
            view?.let{
                timerViewModel.startTimer(it.context)
            }

        }

        binding.BTNStopTimer.setOnClickListener {
            view?.let{
                timerViewModel.stopTimer(it.context)
            }
        }
        return root
    }


    override fun onStart() {
        super.onStart()
        activity?.setTitle(getString(R.string.menu_timer))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

