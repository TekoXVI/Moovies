package edu.dixietech.lukassimonson.moovies.shared.network.dtos

import kotlinx.serialization.Serializable


@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)