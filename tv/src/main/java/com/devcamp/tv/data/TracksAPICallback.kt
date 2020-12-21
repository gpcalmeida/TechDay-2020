package com.devcamp.tv.data

interface TracksAPICallback {
    fun onResponse(response: TracksAPIResponse)
    fun onFailure(throwable: Throwable)
}
