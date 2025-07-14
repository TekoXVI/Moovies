package edu.dixietech.lukassimonson.moovies.shared.database

import edu.dixietech.lukassimonson.moovies.features.detail.model.database.DetailDao

interface MovieDatabase {
    fun detailDao(): DetailDao
}