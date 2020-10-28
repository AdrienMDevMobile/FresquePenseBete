package com.micheladrien.fresquerappel.fragment.timer

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//L'affichage d'un timer
// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder.
// Each data item is just a string in this case that is shown in a TextView.
class TimerViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {

}