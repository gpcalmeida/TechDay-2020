package com.devcamp.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MatchServiceImpl(private val context: Context) : MatchService {
    override fun getMatches(): List<Match> {
        val json = context.resources.assets.open(JSON_PATH).reader()
        val itemType = object : TypeToken<List<Match>>() {}.type
        return Gson().fromJson<List<Match>>(json, itemType)
    }

    companion object {
        const val LOGOS_FOLDER = "logos"
        const val VIDEOS_FOLDER = "matches"
        const val JSON_PATH = "matches.json"
    }
}