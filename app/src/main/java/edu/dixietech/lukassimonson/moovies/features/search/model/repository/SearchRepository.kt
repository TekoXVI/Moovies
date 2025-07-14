package edu.dixietech.lukassimonson.moovies.features.search.model.repository

import edu.dixietech.lukassimonson.moovies.shared.domain.Movie

interface SearchRepository {
    suspend fun search(term: String): List<Movie>
}