package com.techday2020.ui.model

data class Match(
    val homeTeam : String,
    val homeScore : Int,
    val homeDrawable : String,
    val visitorTeam : String,
    val visitorScore : Int,
    val visitorDrawable : String,
    val videoRes : String
)