package network

import android.content.Context
import com.google.gson.Gson

class MatchServiceImpl(private val context: Context) : MatchService {
    override fun getMatches(): Result {
        val json = context.resources.assets.open(JSON_PATH).reader()
        return Gson().fromJson<Result>(json, Result::class.java)
    }

    companion object {
        const val LOGOS_FOLDER = "logos"
        const val VIDEOS_FOLDER = "matches"
        const val JSON_PATH = "matches.json"
    }
}