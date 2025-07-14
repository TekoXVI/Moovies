package edu.dixietech.lukassimonson.moovies.shared.domain

import edu.dixietech.lukassimonson.moovies.shared.database.entities.GenreEntity
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.GenreDto

data class Genre(
    val id: Int,
    val name: String
)

fun GenreDto.toGenre() = Genre(
    id = id,
    name = name
)

fun GenreEntity.toGenre() = Genre(
    id = id,
    name = name
)
