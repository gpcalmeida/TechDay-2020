package com.devcamp.domain

interface TracksCallback{
    fun onSuccess(tracks: List<Tracks>)
    fun onFailure(throwable: Throwable)
}
