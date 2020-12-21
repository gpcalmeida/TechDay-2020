package com.devcamp.network

import com.devcamp.network.MoviesModel
import com.google.gson.annotations.SerializedName


data class TrackModel(
    @SerializedName("id")
    var tracksID: Int,
    @SerializedName("title")
    var trackTitle: String,
    @SerializedName("movies")
    var movies: List<MoviesModel>)