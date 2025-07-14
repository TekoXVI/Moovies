package edu.dixietech.lukassimonson.moovies.features.detail.model.network

import edu.dixietech.lukassimonson.moovies.shared.network.KtorRoot
import edu.dixietech.lukassimonson.moovies.shared.network.NotAuthorizedException
import edu.dixietech.lukassimonson.moovies.shared.network.UnexpectedResponseException
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class KtorDetailNetwork(
    private val root: KtorRoot,
    private val context: CoroutineContext
): DetailNetwork {
    override suspend fun getDetailsForMovie(id: Int) = withContext(context) {
        val response = root.client.get("${root.baseUrl}/3/movie/$id") {
            header("Authorization", "Bearer ${root.token}")
            header("Accept", "application/json")
        }

        return@withContext when (response.status.value) {
            200 -> response.body<MovieDto>()
            401 -> throw NotAuthorizedException()
            else -> throw UnexpectedResponseException()
        }
    }
}