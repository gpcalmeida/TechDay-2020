package com.techday2020.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techday2020.ui.model.Info
import com.techday2020.ui.model.Match
import network.MatchService


class MainController(private val matchesService: MatchService) : ViewModel() {

    private val matchesLiveData = MutableLiveData<List<Match>>()
    fun getMatches(): LiveData<List<Match>> = matchesLiveData

    init {
        loadMatches()
    }

    private fun loadMatches() {
        try {
            val matches = matchesService.getMatches().matches
            matchesLiveData.postValue(
                matches.map {
                    Match(
                        it.home,
                        getScoreByPosition(it.score, 0),
                        getLogo(it.home),
                        it.away,
                        getScoreByPosition(it.score, 1),
                        getLogo(it.away),
                        getVideoPath(it.home, it.away),
                        it.info?.let { info ->
                            Info(info.title, info.content)
                        }
                    )
                }
            )
        } catch (exception: Exception) {

        }
    }

    private fun getScoreByPosition(score: String, position: Int): Int {
        return score.replace(" ", "").split("x")[position].toInt()
    }

    private fun getLogo(team: String): String {
        return "logo-$team.png"
    }

    private fun getVideoPath(home: String, away: String): String {
        return "$home-$away.mp4"
    }
}