package com.example.rv_api.models.launches.upcoming_past


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
data class LaunchItem(


    @SerializedName("details")
    val details: String?,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("launchpad")
    val launchpad: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("links")
    val links: Links,
){
    data class Links(
        @SerializedName("patch")
        val patch: Patch
    )

    data class Patch(
        @SerializedName("large")
        val large: String?,
        @SerializedName("small")
        val small: String?
    )
}


