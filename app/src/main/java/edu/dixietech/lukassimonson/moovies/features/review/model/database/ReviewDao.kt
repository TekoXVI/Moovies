package edu.dixietech.lukassimonson.moovies.features.review.model.database

import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieReviewJoin
import edu.dixietech.lukassimonson.moovies.shared.database.entities.ReviewEntity

interface ReviewDao {
    suspend fun getMovie(id: Int): MovieEntity?
    suspend fun saveMovie(movie: MovieEntity)
    suspend fun saveReview(review: ReviewEntity)
    suspend fun saveJoin(join: MovieReviewJoin)
}