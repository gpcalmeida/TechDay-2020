package com.devcamp.tv.data

import com.devcamp.domain.TracksCallback
import com.devcamp.domain.TracksContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TracksManager : TracksContract{
    private val apiService: TracksAPIService
    private lateinit var tracksCallback: TracksCallback

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        this.apiService = retrofit.create(TracksAPIService::class.java)
    }


    override fun getTracks(callback: TracksCallback) {
        this.tracksCallback = callback
        apiService.getTracksList().enqueue(object : Callback<TracksAPIResponse> {
            override fun onResponse(call: Call<TracksAPIResponse>, response: Response<TracksAPIResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val trackResponse = response.body() as TracksAPIResponse
                    callback.onSuccess(trackResponse.trackList.map {
                        TracksModelMapper.from(
                            it
                        )
                    } )
                }
                else
                    callback.onFailure(Throwable("Não completou requisição ou body nulo"))
            }
            override fun onFailure(call: Call<TracksAPIResponse>, t: Throwable) {
                callback.onFailure(t)
            }
        })

    }

    companion object {
        private val TAG = "TracksManager"
        private val BASE_URL = "https://private-aa6a00-devcamp2019.apiary-mock.com/"
    }

}
