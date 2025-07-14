package edu.dixietech.lukassimonson.moovies.shared.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.dixietech.lukassimonson.moovies.shared.domain.Review

@Entity(tableName = "review")
data class ReviewEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)

fun Review.toReviewEntity() = ReviewEntity(
    id = id,
    title = title,
    body = body
)