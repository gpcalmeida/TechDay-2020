package com.techday2020.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techday2020.R
import com.techday2020.ui.model.Match

class MainController : ViewModel() {

    private val matchesLiveData = MutableLiveData<List<Match>>()
    fun getMatches(): LiveData<List<Match>> = matchesLiveData

    init {
        loadMatches()
    }

    private fun loadMatches() {
        matchesLiveData.postValue(
            listOf(
                Match(
                    homeTeam = "ACG",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_acg,
                    visitorTeam = "INT",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_int,
                    videoDrawable = R.raw.acg_int
                ),
                Match(
                    homeTeam = "BAH",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_bah,
                    visitorTeam = "SAO",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_sao,
                    videoDrawable = R.raw.bah_sao
                ),
                Match(
                    homeTeam = "ACG",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_acg,
                    visitorTeam = "INT",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_int,
                    videoDrawable = R.raw.acg_int
                ),
                Match(
                    homeTeam = "BAH",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_bah,
                    visitorTeam = "SAO",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_sao,
                    videoDrawable = R.raw.bah_sao
                ),
                Match(
                    homeTeam = "ACG",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_acg,
                    visitorTeam = "INT",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_int,
                    videoDrawable = R.raw.acg_int
                ),
                Match(
                    homeTeam = "BAH",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_bah,
                    visitorTeam = "SAO",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_sao,
                    videoDrawable = R.raw.bah_sao
                )
            )
        )
    }
}