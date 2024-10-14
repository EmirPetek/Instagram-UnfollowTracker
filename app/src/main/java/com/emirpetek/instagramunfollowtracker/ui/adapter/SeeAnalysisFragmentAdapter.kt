package com.emirpetek.instagramunfollowtracker.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData

class SeeAnalysisFragmentAdapter(
    val context: Context,
    val list: List<AnalysisData>
): RecyclerView.Adapter<SeeAnalysisFragmentAdapter.CardHolder>() {

    inner class CardHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        TODO("Not yet implemented")
    }

}