package com.devcamp.tv.data

import com.devcamp.tv.data.TracksAPIResponse
import retrofit2.Call
import retrofit2.http.GET

interface TracksAPIService{
    @GET("home")
    fun getTracksList(): Call<TracksAPIResponse>
}
