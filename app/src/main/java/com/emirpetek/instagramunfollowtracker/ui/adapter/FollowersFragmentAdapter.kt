package com.emirpetek.instagramunfollowtracker.ui.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.data.DataItem
import com.emirpetek.instagramunfollowtracker.util.CalculateTime

class FollowersFragmentAdapter(
    val mContext: Context,
    val list : List<DataItem>
): RecyclerView.Adapter<FollowersFragmentAdapter.CardViewHolder>() {

    inner class CardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvUsername : TextView = view.findViewById(R.id.textViewCardUserName)
        val tvTimestamp : TextView = view.findViewById(R.id.textViewCardUnixtimestamp)
        val cardUser: CardView = view.findViewById(R.id.cardUserInformation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.card_user_list,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]

        for (i in item.string_list_data){
            holder.tvUsername.text = i.value
            holder.tvTimestamp.text = CalculateTime(mContext).unixtsToDate(i.timestamp.toString())
            holder.cardUser.setOnClickListener { it ->
                val profileURL = i.href
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(profileURL)

                intent.setPackage("com.instagram.android")
                try {
                    mContext.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // Eğer Instagram yüklü değilse tarayıcıda açar
                    mContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(profileURL)))
                }
            }
        }



    }


}