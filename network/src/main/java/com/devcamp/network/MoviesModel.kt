package com.devcamp.network

import com.google.gson.annotations.SerializedName

data class MoviesModel(
    @SerializedName("id")
    var movieID: Int,
    @SerializedName("title")
    var movieTitle: String,
    @SerializedName("date")
    var date : String,
    @SerializedName("card_image")
    var cardImage : String,
    @SerializedName("thumb_image")
    var thumbImage : String,
    @SerializedName("synopsis")
    var movieDescription : String,
    @SerializedName("duration")
    var duration : String,
    @SerializedName("yt_video_id")
    var youtubeID : Int,
    @SerializedName("categories")
    var categories : List<String>
)