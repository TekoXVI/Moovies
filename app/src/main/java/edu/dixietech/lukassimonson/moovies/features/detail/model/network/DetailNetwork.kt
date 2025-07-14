package edu.dixietech.lukassimonson.moovies.features.detail.model.network

import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto

interface DetailNetwork {
    suspend fun getDetailsForMovie(id: Int): MovieDto
}