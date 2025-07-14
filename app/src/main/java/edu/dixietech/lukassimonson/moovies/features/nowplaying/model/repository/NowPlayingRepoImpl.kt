package edu.dixietech.lukassimonson.moovies.features.nowplaying.model.repository

import edu.dixietech.lukassimonson.moovies.features.nowplaying.model.network.NowPlayingNetwork
import edu.dixietech.lukassimonson.moovies.shared.domain.toMovie
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NowPlayingRepoImpl(
    private val network: NowPlayingNetwork,
    private val context: CoroutineContext
): NowPlayingRepository {
    override suspend fun getNowPlaying() = withContext(context) {
        network.getNowPlaying().map(MovieDto::toMovie)
    }
}