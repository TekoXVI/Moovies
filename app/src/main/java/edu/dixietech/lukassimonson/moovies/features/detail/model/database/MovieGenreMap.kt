package edu.dixietech.lukassimonson.moovies.features.detail.model.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import edu.dixietech.lukassimonson.moovies.shared.database.entities.GenreEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieGenreJoin


data class MovieGenreMap(
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id", // id in GenreEntity
        associateBy = Junction(
            value = MovieGenreJoin::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    val genreEntities: List<GenreEntity>
)