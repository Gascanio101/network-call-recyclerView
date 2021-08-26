package com.example.rv_api

import com.example.rv_api.models.launches.upcoming.Upcoming
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {

    @GET ("/launches/latest/")
    suspend fun getUpcomingLaunches(): Response<Upcoming>
}