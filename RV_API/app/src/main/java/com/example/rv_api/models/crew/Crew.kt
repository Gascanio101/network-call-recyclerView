package com.example.rv_api.models.crew

import com.example.rv_api.models.launches.upcoming_past.LaunchItem
import com.google.gson.annotations.SerializedName

class Crew: ArrayList<Crew.Astronaut>() {

    data class Astronaut(val name: String,
                         val agency: String,
                         val image: String?,
                         val status: String,
                         val id: String)
}