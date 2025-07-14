package edu.dixietech.lukassimonson.moovies.features.nowplaying.model.repository

import edu.dixietech.lukassimonson.moovies.shared.domain.Movie

interface NowPlayingRepository {
    suspend fun getNowPlaying(): List<Movie>
}