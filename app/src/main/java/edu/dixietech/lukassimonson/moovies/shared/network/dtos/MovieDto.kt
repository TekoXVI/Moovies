package edu.dixietech.lukassimonson.moovies.shared.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("poster_path")
    val posterPath: String?,

    val genres: List<GenreDto>?,
    val runtime: Int?,

    @SerialName("vote_average")
    val rating: Double?
)
