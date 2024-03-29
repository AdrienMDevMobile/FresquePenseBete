package com.micheladrien.fresquerappel.View.views.timer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.fresquerappel.R
import com.micheladrien.fresquerappel.Data.datas.TimerModel
import com.micheladrien.fresquerappel.View.tools.TimerStringTool


//Gestionnaire des ViewHolder
class TimerAdapter(private val timer_set: ArrayList<TimerModel>) : RecyclerView.Adapter<TimerAdapter.ViewHolder>()  {

    companion object{
        var max_length = 23
    }
    //The method creates and initializes the ViewHolder and its associated View,
    // but does not fill in the view's contents—the ViewHolder has not yet been bound to specific data.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.timer_view_item, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(view)
    }

    //The method fetches the appropriate data and uses the data to fill in the view holder's layout.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        //name affichera exactement X characteres
        var name = timer_set[position].name
        if(name != null){
            if(name.length >=max_length)
                name = name.subSequence(0, max_length).toString()
            else {
                while(name.length < max_length) name+= " "
            }
        }
        else {
            name = ""
        }


        holder.tv_timer_name.text = name
        holder.tv_timer_value.text = TimerStringTool.fromSecToTimeString(timer_set[position].time_value)

    }

    //RecyclerView calls this method to get the size of the data set.
    // For example, in an address book app, this might be the total number of addresses.
    // RecyclerView uses this to determine when there are no more items that can be displayed.
    override fun getItemCount() = timer_set.size


    //L'affichage d'un timer
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    //TODO ???? Constructeur : id du timer, Init : récupère le nom et la durée associée à l'id du timer
    //1 : 5 mns 2: 20 mns 3 : 10 mns 4 : 10 mns 5 : 10 mns
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tv_timer_name : TextView
        val tv_timer_value : TextView

        init {
            // Define click listener for the ViewHolder's View.
            //Fait le lien avec le textView dans l'xml
            tv_timer_name = view.findViewById(R.id.tv_timer_name)
            tv_timer_value = view.findViewById(R.id.tv_timer_value)
        }
    }
}

