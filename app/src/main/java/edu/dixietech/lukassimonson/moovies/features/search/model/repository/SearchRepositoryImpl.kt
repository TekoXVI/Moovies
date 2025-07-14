package edu.dixietech.lukassimonson.moovies.features.search.model.repository

import edu.dixietech.lukassimonson.moovies.features.search.model.network.SearchNetwork
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import edu.dixietech.lukassimonson.moovies.shared.domain.toMovie
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SearchRepositoryImpl(
    val network: SearchNetwork,
    val context: CoroutineContext
): SearchRepository {

    override suspend fun search(term: String) = withContext(context) {
        network.search(term).map(MovieDto::toMovie)
    }
}