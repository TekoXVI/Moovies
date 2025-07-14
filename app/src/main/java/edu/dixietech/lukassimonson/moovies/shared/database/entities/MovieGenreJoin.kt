package edu.dixietech.lukassimonson.moovies.shared.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    primaryKeys = ["movieId", "genreId"],
    foreignKeys = [
        ForeignKey(
            MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            GenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["genreId"],
            onDelete = CASCADE
        )
    ]
)
data class MovieGenreJoin(
    val movieId: Int,
    val genreId: Int
)