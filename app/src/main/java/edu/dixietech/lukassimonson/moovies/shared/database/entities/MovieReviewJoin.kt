package edu.dixietech.lukassimonson.moovies.shared.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    primaryKeys = ["movieId", "reviewId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ReviewEntity::class,
            parentColumns = ["id"],
            childColumns = ["reviewId"],
            onDelete = CASCADE
        )
    ]
)
data class MovieReviewJoin(
    val movieId: Int,
    val reviewId: Int
)