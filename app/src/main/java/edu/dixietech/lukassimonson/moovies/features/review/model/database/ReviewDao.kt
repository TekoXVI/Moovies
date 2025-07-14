package edu.dixietech.lukassimonson.moovies.features.review.model.database

import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity

interface ReviewDao {
    suspend fun getMovie(id: Int): MovieEntity?
    suspend fun saveMovie(movie: MovieEntity)
}