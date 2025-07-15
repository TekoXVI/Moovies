package edu.dixietech.lukassimonson.moovies.features.detail.model.repository

import edu.dixietech.lukassimonson.moovies.shared.domain.Movie

interface DetailRepository {
    suspend fun getMovieDetails(id: Int): Movie?
}