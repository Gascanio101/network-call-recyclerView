package com.example.rv_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpaceXService {

    private val BASE_URL = "https://api.spacexdata.com/"

    // Service builder for Network calls
    fun getSpaceXDataService() : SpaceXApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(SpaceXApi::class.java)
    }
}