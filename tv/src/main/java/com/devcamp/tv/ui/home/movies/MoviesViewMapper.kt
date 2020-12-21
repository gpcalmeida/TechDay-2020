package com.devcamp.tv.ui.home.movies

import com.devcamp.domain.Movies

class MoviesViewMapper{
    companion object {
        fun from(movie: Movies?) = MoviesViewModel(
            movieID = movie?.movieID,
            movieTitle = movie?.movieTitle,
            movieDuration = movie?.movieDuration,
            movieSinopse = movie?.movieSinopse,
            movieDate = movie?.movieDate,
            cardImage = movie?.cardImage,
            thumbImage = movie?.thumbImage,
            youtubeID = movie?.youtubeID,
            categories = movie?.categories
        )
    }
}