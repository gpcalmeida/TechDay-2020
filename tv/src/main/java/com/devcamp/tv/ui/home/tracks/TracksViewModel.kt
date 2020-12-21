package com.devcamp.tv.ui.home.tracks

import com.devcamp.tv.ui.home.movies.MoviesViewModel

data class TracksViewModel(var trackTitle : String = "", var movies : MutableList<MoviesViewModel>)