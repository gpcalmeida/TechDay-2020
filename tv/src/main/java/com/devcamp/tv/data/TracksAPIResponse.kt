package com.devcamp.tv.data

import com.devcamp.tv.data.TrackModel
import com.google.gson.annotations.SerializedName

class TracksAPIResponse {
    @SerializedName("tracks")
    var trackList: List<TrackModel> = listOf()
}
