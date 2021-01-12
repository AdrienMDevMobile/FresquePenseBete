package com.micheladrien.fresquerappel.fragment.timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.datas.TimerModel

//Gestionnaire des ViewHolder
class TimerAdapter(private val timer_set: ArrayList<TimerModel>) : RecyclerView.Adapter<TimerViewHolder>()  {

    //The method creates and initializes the ViewHolder and its associated View,
    // but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.timer_view_item, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return TimerViewHolder(view)

    }

    //The method fetches the appropriate data and uses the data to fill in the view holder's layout.
    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //holder.attribut =

        //name affichera exactement trois charactères
        var name = timer_set[position].name
        if(name != null){
            if(name.length >=3)
                name = name.subSequence(0,3).toString()
            else {
                while(name.length < 3) name+= " "
            }
        }
        else {
            name = ""
        }


        holder.tv_timer_name.text = name
        holder.tv_timer_value.text = timer_set[position].time_value.toString()

    }

    //RecyclerView calls this method to get the size of the data set.
    // For example, in an address book app, this might be the total number of addresses.
    // RecyclerView uses this to determine when there are no more items that can be displayed.
    override fun getItemCount() = timer_set.size

}