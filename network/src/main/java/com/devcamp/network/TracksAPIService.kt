package com.devcamp.network

import com.devcamp.network.TracksAPIResponse
import retrofit2.Call
import retrofit2.http.GET

interface TracksAPIService{
    @GET("home")
    fun getTracksList(): Call<TracksAPIResponse>
}
