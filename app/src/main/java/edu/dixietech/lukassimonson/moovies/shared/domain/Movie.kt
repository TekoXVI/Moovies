package edu.dixietech.lukassimonson.moovies.shared.domain

import edu.dixietech.lukassimonson.moovies.shared.database.entities.GenreEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.GenreDto
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val backdropPath: String?,
    val posterPath: String?,
    val genres: List<Genre>?,
    val runtime: Int?,
    val rating: Double?
)

fun MovieDto.toMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    posterPath = posterPath,
    genres = genres?.map(GenreDto::toGenre),
    runtime = runtime,
    rating = rating
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    posterPath = posterPath,
    genres = genres?.map(GenreEntity::toGenre),
    runtime = runtime,
    rating = rating
)