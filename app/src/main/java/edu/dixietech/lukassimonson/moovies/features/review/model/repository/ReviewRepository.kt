package edu.dixietech.lukassimonson.moovies.features.review.model.repository

import edu.dixietech.lukassimonson.moovies.shared.domain.Movie

interface ReviewRepository {
    suspend fun getMovieReview(id: Int): Movie?
}