package com.devcamp.network

import com.devcamp.domain.Movies
import com.devcamp.network.MoviesModel

class MoviesModelMapper{
    companion object {
        fun from(movies: MoviesModel) = Movies(
            movieID = movies.movieID,
            movieTitle = movies.movieTitle,
            movieDate = movies.date,
            cardImage = movies.cardImage,
            thumbImage = movies.thumbImage,
            movieSinopse = movies.movieDescription,
            movieDuration = movies.duration,
            youtubeID = movies.youtubeID,
            categories = movies.categories)
    }
}