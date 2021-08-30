package com.example.rv_api.models.launches

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rv_api.R
import com.example.rv_api.models.launches.upcoming_past.LaunchItem
import com.example.rv_api.view.activities.LaunchesDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.launches_recycler_cell.view.*

class LaunchesAdapter(var launchesList: List<LaunchItem>) :
    RecyclerView.Adapter<LaunchesAdapter.LaunchesViewHolder>() {

    class LaunchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {

        return LaunchesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.launches_recycler_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {

        holder.itemView.tv_name.text = launchesList[position].name
        holder.itemView.tv_flight_number.text = launchesList[position].flightNumber.toString()

        val launchImageHolder = holder.itemView.launches_home_iv
        val launchImageIntent = launchesList[position].links.patch.large ?: "No image"

        // launchImageHolder.setImageResource(R.drawable.default_launch)

        val launchImage = launchesList[position].links.patch.small

        Picasso.with(holder.itemView.context)
            .load(launchImage)
            .placeholder(R.drawable.default_launch)
            .resize(800, 800)
            .centerInside()
            .into(launchImageHolder)

        val intent: Intent = Intent(holder.itemView.context, LaunchesDetailActivity::class.java)
        holder.itemView.setOnClickListener {

            intent.putExtra("name", launchesList[position].name)
            intent.putExtra("details", launchesList[position].details)
            intent.putExtra("flightNumber", launchesList[position].flightNumber.toString())
            intent.putExtra("image", launchImageIntent)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return launchesList.size
    }
}