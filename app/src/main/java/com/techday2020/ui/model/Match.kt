package com.techday2020.ui.model

data class Match(
    val homeTeam : String,
    val homeScore : Int,
    val homeDrawable : Int,
    val visitorTeam : String,
    val visitorScore : Int,
    val visitorDrawable : Int,
    val videoDrawable : Int
)