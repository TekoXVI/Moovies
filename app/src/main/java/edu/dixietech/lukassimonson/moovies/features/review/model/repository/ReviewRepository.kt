package edu.dixietech.lukassimonson.moovies.features.review.model.repository

import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import edu.dixietech.lukassimonson.moovies.shared.domain.Review

interface ReviewRepository {
    suspend fun getMovieReview(id: Int): Movie?
    suspend fun saveMovieReview(movie: Movie, review: Review)
    suspend fun saveMovieRating(movie: Movie, rating: Double)
}