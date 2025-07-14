package edu.dixietech.lukassimonson.moovies.features.nowplaying.model.network

import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto

interface NowPlayingNetwork {
    suspend fun getNowPlaying(): List<MovieDto>
}