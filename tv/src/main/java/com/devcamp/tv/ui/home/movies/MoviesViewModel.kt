package com.devcamp.tv.ui.home.movies

data class MoviesViewModel(
    var movieID : Int? = 0,
    var movieTitle : String? = "",
    var movieDate: String? = "",
    var cardImage : String? = "",
    var thumbImage : String? = "",
    var movieSinopse : String? = "",
    var movieDuration : String? = "",
    var youtubeID : String? = "",
    var categories : List<String>? = listOf())