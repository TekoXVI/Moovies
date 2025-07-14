package edu.dixietech.lukassimonson.moovies.features.search.model.network

import edu.dixietech.lukassimonson.moovies.features.nowplaying.model.network.NowPlayingNetwork
import edu.dixietech.lukassimonson.moovies.shared.network.KtorRoot
import edu.dixietech.lukassimonson.moovies.shared.network.NotAuthorizedException
import edu.dixietech.lukassimonson.moovies.shared.network.UnexpectedResponseException
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.ApiResponse
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class KtorSearchNetwork(
    private val root: KtorRoot,
    private val context: CoroutineContext
): SearchNetwork {

    override suspend fun search(term: String) = withContext(context) {
        val response = root.client.get("${root.baseUrl}/3/search/movie") {
            parameter("query", term)
            header("Authorization", "Bearer ${root.token}")
            header("Accept", "application/json")
        }

        return@withContext when (response.status.value) {
            200 -> response.body<ApiResponse<MovieDto>>().results
            401 -> throw NotAuthorizedException()
            else -> throw UnexpectedResponseException()
        }
    }
}