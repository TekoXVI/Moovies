package edu.dixietech.lukassimonson.moovies.features.search.model.network

import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto

interface SearchNetwork {
    suspend fun search(term: String): List<MovieDto>
}