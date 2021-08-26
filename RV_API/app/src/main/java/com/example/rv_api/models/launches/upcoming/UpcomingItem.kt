package com.example.rv_api.models.launches.upcoming


import com.google.gson.annotations.SerializedName

data class UpcomingItem(

    /* TODO Future data-structure
    @SerializedName("crew")
    val crew: List<Any>,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("date_precision")
    val datePrecision: String,
    @SerializedName("date_unix")
    val dateUnix: Int,
    @SerializedName("date_utc")
    val dateUtc: String,
    */

    @SerializedName("details")
    val details: String?,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("launchpad")
    val launchpad: String,
    @SerializedName("name")
    val name: String
)