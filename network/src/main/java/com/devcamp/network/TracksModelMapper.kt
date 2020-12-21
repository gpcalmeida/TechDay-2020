package com.devcamp.network

import com.devcamp.domain.Tracks

class TracksModelMapper{
    companion object {
        fun from(model: TrackModel) = Tracks(
            trackID = model.tracksID,
            trackTitle = model.trackTitle,
            movies = model.movies.map { MoviesModelMapper.from(it) })
    }
}