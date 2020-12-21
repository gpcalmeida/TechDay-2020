package com.devcamp.tv.ui.home

import com.devcamp.tv.ui.home.tracks.TracksViewModel

interface HomeView {
fun addTracks(tracksList : MutableList<TracksViewModel>)
fun showError()
fun showLoad()
fun removeLoad()
}