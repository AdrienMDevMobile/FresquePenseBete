package com.micheladrien.fresquerappel.fragment.timer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.databinding.DialogueFragmentRechercheBinding
import com.micheladrien.fresquerappel.databinding.FragmentTimerBinding
import com.micheladrien.fresquerappel.fragment.relation.RelationViewModel

class TimerFragment : Fragment() {

    private lateinit var timerViewModel: TimerViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TimerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var _binding: FragmentTimerBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root = binding.root

        //TODO Deplacer cela ailleur (mais recommandations disent de le mettre au max d'endroits au cas où)
        createNotificationChannel()
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

        timerViewModel.timerLiveData.observe(viewLifecycleOwner, {

            //Dans l'observe : ecrase et refait le RecyclerViewAdapter
            viewAdapter = TimerAdapter(it)
            // use a linear layout manager
            recyclerView.layoutManager = LinearLayoutManager(context)
            // specify an viewAdapter (see also next example)
            recyclerView.adapter = viewAdapter

        })
        //Log.d("ami","fin set up")


        //TODO Donner la fonction delete de ma VM dans le bouton X (a faire dans l'adapter ?) Il me faut probablement creer une classe custom OnClickListener

        //timerViewModel
        binding.BTNStartTimer.setOnClickListener {
            val builder = NotificationCompat.Builder(view?.context!!, "ID_NOTIFICATION")
                    .setSmallIcon(R.drawable.main_alerte)
                    .setContentTitle("Titre")
                    .setContentText("Texte")
                    .setPriority(NotificationCompat.PRIORITY_MAX)
            with(NotificationManagerCompat.from(view?.context!!)) {
                // notificationId is a unique int for each notification that you must define
                notify(2, builder.build())
            }
        /*
            Log.d("onclickTimer", "1")
            timerViewModel.startTimer() */
        }
        return root
    }

    //TODO Deplacer cela ailleur
    //TODO Id channel doit etre contenu dans un fichier
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("ID_NOTIFICATION", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

