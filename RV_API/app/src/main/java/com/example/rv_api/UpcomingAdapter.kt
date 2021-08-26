package com.example.rv_api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rv_api.models.launches.upcoming.UpcomingItem

class UpcomingAdapter (var upcomingList: List<UpcomingItem>)
    : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

        class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {

        return UpcomingViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.upcoming_recycler_cell, parent, false))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

}