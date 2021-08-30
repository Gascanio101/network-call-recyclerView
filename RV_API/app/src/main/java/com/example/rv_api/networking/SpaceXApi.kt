package com.example.rv_api.networking

import com.example.rv_api.models.crew.Crew
import com.example.rv_api.models.launches.Launches
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {

    @GET ("v5/launches/upcoming")
    suspend fun getUpcomingLaunches(): Response<Launches>

    @GET("v5/launches/past")
    suspend fun getPastLaunches(): Response<Launches>

    @GET("v4/crew")
    suspend fun getCrewList(): Response<Crew>
}