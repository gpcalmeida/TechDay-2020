package com.devcamp.tv

import android.app.Activity
import android.content.Intent
import com.devcamp.tv.ui.details.DetailsActivity
import com.devcamp.tv.ui.main.MainActivity

class Navigator(var activity: Activity?) {

    fun goToHomeActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }

    fun goToDetailsActivity(movieTitle : String? = "",
                            movieThumb : String? = "",
                            movieDuration : String? = "",
                            movieSinopse : String? = ""){

        val intent = Intent(activity, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.MOVIE_TITLE, movieTitle)
            putExtra(DetailsActivity.MOVIE_THUMB, movieThumb)
            putExtra(DetailsActivity.MOVIE_DURATION, movieDuration)
            putExtra(DetailsActivity.MOVIE_SINOPSE, movieSinopse)

        }

        activity?.startActivity(intent)
    }
}