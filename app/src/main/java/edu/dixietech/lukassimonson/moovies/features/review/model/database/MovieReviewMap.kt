package edu.dixietech.lukassimonson.moovies.features.review.model.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieReviewJoin
import edu.dixietech.lukassimonson.moovies.shared.database.entities.ReviewEntity

data class MovieReviewMap(
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieReviewJoin::class,
            parentColumn = "movieId",
            entityColumn = "reviewId"
        )
    )
    val reviewEntity: ReviewEntity
)
