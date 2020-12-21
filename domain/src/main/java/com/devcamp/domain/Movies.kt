package com.devcamp.domain

class Movies(
   val movieID : Int? = 0,
   val movieTitle : String? = "",
   val movieDate : String? = "",
   val cardImage : String? = "",
   val thumbImage : String? = "",
   val movieSinopse : String? = "",
   val movieDuration : String? = "",
   val youtubeID : String? = "",
   val categories : List<String>? = listOf())