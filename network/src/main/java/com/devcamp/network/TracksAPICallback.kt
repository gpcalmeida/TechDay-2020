package com.devcamp.network

interface TracksAPICallback {
    fun onResponse(response: TracksAPIResponse)
    fun onFailure(throwable: Throwable)
}
