package com.devcamp.network

import android.content.Context

class MatchServiceImpl(private val context: Context): MatchService {
    override fun getMatches(): List<Match> {
        //val json = context.resources.assets.open()
        return emptyList()
    }
}