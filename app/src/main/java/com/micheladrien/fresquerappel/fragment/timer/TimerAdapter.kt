package com.micheladrien.fresquerappel.fragment.timer

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//Gestionnaire des ViewHolder
class TimerAdapter(private val timer_set: Array<String>) : RecyclerView.Adapter<TimerViewHolder>() {

    /*



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }



}

     */

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        TODO("Not yet implemented")
        /*
                // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as TextView
        // set the view's size, margins, paddings and layout parameters
        ...
        return MyViewHolder(textView)
         */
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        TODO("Not yet implemented")
        /*
                // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = myDataset[position]
         */
    }
    
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = timer_set.size
}