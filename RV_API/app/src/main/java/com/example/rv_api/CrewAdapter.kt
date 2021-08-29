package com.example.rv_api

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rv_api.models.crew.Crew
import com.example.rv_api.view.activities.CrewProfileActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crew_recycler_cell.view.*

class CrewAdapter(val crewList: MutableList<Crew.Astronaut>)
    : RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    class CrewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewAdapter.CrewViewHolder {
        return CrewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.crew_recycler_cell, parent, false))
    }

    override fun onBindViewHolder(holder: CrewAdapter.CrewViewHolder, position: Int) {

        // Inflate the recyclerView cell with some relevant info.
        holder.itemView.tv_crew_name.text = crewList[position].name
        val profilePictureView = holder.itemView.iv_crewList
        Picasso.with(holder.itemView.context)
            .load(crewList[position].image)
            .resize(800,800)
            .centerInside()
            .into(profilePictureView)

        // Create an Intent to pass data to the profile activity when tapping the astronaut's cell
        val intent: Intent = Intent(holder.itemView.context, CrewProfileActivity::class.java)
        holder.itemView.setOnClickListener {

            intent.putExtra("name", crewList[position].name)
            intent.putExtra("image", crewList[position].image)
            intent.putExtra("agency", crewList[position].agency)
            intent.putExtra("status", crewList[position].status)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return crewList.size
    }
}