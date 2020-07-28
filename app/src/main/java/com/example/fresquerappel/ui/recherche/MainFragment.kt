package com.example.fresquerappel.ui.recherche

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.fresquerappel.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = context?.let {
            MainViewModel.getInstance(
                this, it
            )
        }!!

        val root = inflater.inflate(R.layout.fragment_main, container, false)

        //Explication
        val textView: TextView = root.findViewById(R.id.text_explication)
        mainViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //Id des deux cartes
        mainViewModel.carte1.observe(viewLifecycleOwner, Observer {
            txt_id_carte1.text = it.toString()
        })
        mainViewModel.carte2.observe(viewLifecycleOwner, Observer {
            txt_id_carte2.text = it.toString()
        })
        mainViewModel.relation.observe(viewLifecycleOwner, Observer {
            drawRelation(it)
        })

        return root
    }

    private fun drawRelation(relationDirection : RelationDirection){
        if(relationDirection == RelationDirection.UP){
            txt_relation.text = context?.getString(R.string.relation_up)
            context?.let { ContextCompat.getColor(it, R.color.green_correct) }?.let {
                txt_relation.setTextColor(
                    it
                )
            }
        }

        if(relationDirection == RelationDirection.DOWN){
            txt_relation.text = context?.getString(R.string.relation_down)
            context?.let { ContextCompat.getColor(it, R.color.green_correct) }?.let {
                txt_relation.setTextColor(
                    it
                )
            }
        }


        if(relationDirection == RelationDirection.INCORRECT){
            txt_relation.text = context?.getString(R.string.relation_incorrect)
            context?.let { ContextCompat.getColor(it, R.color.red_incorrect) }?.let {
                txt_relation.setTextColor(
                    it
                )
            }
        }

        if(relationDirection == RelationDirection.UPDOWN){
            txt_relation.text = context?.getString(R.string.relation_updown)
            context?.let { ContextCompat.getColor(it, R.color.green_correct) }?.let {
                txt_relation.setTextColor(
                    it
                )
            }
        }


        if(relationDirection == RelationDirection.NONE){
            txt_relation.text = context?.getString(R.string.relation_none)
            context?.let { ContextCompat.getColor(it, R.color.gray_missing) }?.let {
                txt_relation.setTextColor(
                    it
                )
            }
        }

    }
}