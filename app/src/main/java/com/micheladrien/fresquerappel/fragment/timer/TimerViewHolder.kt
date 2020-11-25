package com.micheladrien.fresquerappel.fragment.timer

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.fresquerappel.R

//L'affichage d'un timer
// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder.
// Each data item is just a string in this case that is shown in a TextView.
//TODO ???? Constructeur : id du timer, Init : récupère le nom et la durée associée à l'id du timer
//1 : 5 mns 2: 20 mns 3 : 10 mns 4 : 10 mns 5 : 10 mns
class TimerViewHolder(val view: View) : RecyclerView.ViewHolder(view) { /*
    val tv_timer_name : TextView

    init {
        // Define click listener for the ViewHolder's View.
        //Fait le lien avec le textView dans l'xml
        tv_timer_name = view.findViewById(R.id.tv_timer_name)
    }
        */
}