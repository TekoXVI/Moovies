package edu.dixietech.lukassimonson.moovies.features.detail.model.database

import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity

interface DetailDao {
    suspend fun getMovie(id: Int): MovieEntity?
    suspend fun saveMovie(movie: MovieEntity)
}