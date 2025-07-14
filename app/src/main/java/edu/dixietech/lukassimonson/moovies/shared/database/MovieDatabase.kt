package edu.dixietech.lukassimonson.moovies.shared.database

import edu.dixietech.lukassimonson.moovies.features.detail.model.database.DetailDao
import edu.dixietech.lukassimonson.moovies.features.review.model.database.ReviewDao

interface MovieDatabase {
    fun detailDao(): DetailDao
    fun reviewDao(): ReviewDao
}