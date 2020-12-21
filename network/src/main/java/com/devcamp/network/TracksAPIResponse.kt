package com.devcamp.network

import com.devcamp.network.TrackModel
import com.google.gson.annotations.SerializedName

class TracksAPIResponse {
    @SerializedName("tracks")
    var trackList: List<TrackModel> = listOf()
}
