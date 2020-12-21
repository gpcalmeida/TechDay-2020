package com.devcamp.tv.ui.home

import com.devcamp.domain.Tracks
import com.devcamp.domain.TracksCallback
import com.devcamp.domain.TracksUseCase
import com.devcamp.tv.ui.home.tracks.TracksViewMapper

class HomePresenter(
    private val view: HomeView,
    private val getTracks: TracksUseCase) : TracksCallback{
    private var isPaused = false
    private var isLoading = false
    private var tracksList = listOf<Tracks>()

    fun onCreateView() {
        view.showLoad()
        isLoading = true
        getTracks.with(this).execute()
    }

    fun onResume() {
        isPaused = false
        view.removeLoad()
    }

    fun onViewPaused() {
        isPaused = true
    }

    fun onClickTryAgain() {
        view.showLoad()
        getTracks.with(this).execute()
    }

    override fun onSuccess(tracks: List<Tracks>) {
        if (!isPaused) {
            isLoading = false
            view.addTracks(tracks.map { TracksViewMapper.from(it) }.toMutableList())
            this.tracksList = tracks
        }
    }

    override fun onFailure(throwable: Throwable) {
        if (!isPaused) {
            view.showError()
        }
    }
}

