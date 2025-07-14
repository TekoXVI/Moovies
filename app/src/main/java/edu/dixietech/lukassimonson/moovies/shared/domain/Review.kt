package edu.dixietech.lukassimonson.moovies.shared.domain

import edu.dixietech.lukassimonson.moovies.shared.database.entities.ReviewEntity

data class Review(
    val id: Int,
    val rating: Int,
    val title: String,
    val body: String
)

fun ReviewEntity.toReview() = Review(
    id = id,
    rating = rating,
    title = title,
    body = body
)