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
                    homeTeam = "CAM",
                    homeScore = 2,
                    homeDrawable = R.drawable.ic_cam,
                    visitorTeam = "BOT",
                    visitorScore = 1,
                    visitorDrawable = R.drawable.ic_bot,
                    videoDrawable = R.raw.acg_int
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
                    homeScore = 1,
                    homeDrawable = R.drawable.ic_bah,
                    visitorTeam = "SAO",
                    visitorScore = 3,
                    visitorDrawable = R.drawable.ic_sao,
                    videoDrawable = R.raw.bah_sao
                ),
                Match(
                    homeTeam = "CFC",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_cfc,
                    visitorTeam = "COR",
                    visitorScore = 1,
                    visitorDrawable = R.drawable.ic_cor,
                    videoDrawable = R.raw.acg_int
                ),
                Match(
                    homeTeam = "FLU",
                    homeScore = 0,
                    homeDrawable = R.drawable.ic_flu,
                    visitorTeam = "BGT",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_bgt,
                    videoDrawable = R.raw.bah_sao
                ),

                Match(
                    homeTeam = "FOR",
                    homeScore = 1,
                    homeDrawable = R.drawable.ic_for,
                    visitorTeam = "GOI",
                    visitorScore = 1,
                    visitorDrawable = R.drawable.ic_goi,
                    videoDrawable = R.raw.bah_sao
                ),

                Match(
                    homeTeam = "PAL",
                    homeScore = 3,
                    homeDrawable = R.drawable.ic_pal,
                    visitorTeam = "ATL",
                    visitorScore = 0,
                    visitorDrawable = R.drawable.ic_atl,
                    videoDrawable = R.raw.bah_sao
                ),

                Match(
                    homeTeam = "SAN",
                    homeScore = 1,
                    homeDrawable = R.drawable.ic_san,
                    visitorTeam = "SPT",
                    visitorScore = 1,
                    visitorDrawable = R.drawable.ic_spt,
                    videoDrawable = R.raw.bah_sao
                ),

                Match(
                    homeTeam = "VAS",
                    homeScore = 1,
                    homeDrawable = R.drawable.ic_vas,
                    visitorTeam = "CEA",
                    visitorScore = 4,
                    visitorDrawable = R.drawable.ic_cea,
                    videoDrawable = R.raw.bah_sao
                )
            )
        )
    }
}