package com.emirpetek.instagramunfollowtracker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData
import com.emirpetek.instagramunfollowtracker.util.CalculateTime

class SeeAnalysisListFragmentAdapter(
    val context: Context,
    val list: List<AnalysisData>
): RecyclerView.Adapter<SeeAnalysisListFragmentAdapter.CardHolder>() {

    inner class CardHolder(view: View): RecyclerView.ViewHolder(view){
        val tvAnalysisTime: TextView = view.findViewById(R.id.textViewCardSeeAnalysisTime)
        val layout : LinearLayout = view.findViewById(R.id.layoutCardSeeAnalysis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_see_analysis,parent,false)
        return CardHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val item = list[position]
        holder.tvAnalysisTime.text = CalculateTime(context).unixtsToDate((item.timestamp/1000).toString())
        holder.layout.setOnClickListener { it ->
            Navigation.findNavController(it).navigate(R.id.action_seeAnalysisListFragment_to_seeAnalysisFragment)
        }
    }

}