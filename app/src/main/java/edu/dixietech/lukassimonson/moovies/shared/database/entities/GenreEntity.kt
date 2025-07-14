package edu.dixietech.lukassimonson.moovies.shared.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.GenreDto

@Entity("genre")
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)

fun GenreDto.toGenreEntity() = GenreEntity(
    id = id,
    name = name
)