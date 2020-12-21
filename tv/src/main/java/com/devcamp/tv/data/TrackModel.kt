package com.devcamp.tv.data

import com.google.gson.annotations.SerializedName


data class TrackModel(
    @SerializedName("id")
    var tracksID: Int,
    @SerializedName("title")
    var trackTitle: String,
    @SerializedName("movies")
    var movies: List<MoviesModel>)