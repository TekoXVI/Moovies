package edu.dixietech.lukassimonson.moovies.shared.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.dixietech.lukassimonson.moovies.features.detail.model.database.RoomDetailDao
import edu.dixietech.lukassimonson.moovies.shared.database.entities.GenreEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieGenreJoin

@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class,
        MovieGenreJoin::class
    ],
    version = 2,
    exportSchema = true,
)
abstract class RoomMovieDatabase : RoomDatabase(), MovieDatabase {
    abstract override fun detailDao(): RoomDetailDao
}

object Migrations {
    class OneToTwo: Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Create new table
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS movie_tmp(
                    id INTEGER PRIMARY KEY NOT NULL,
                    title TEXT NOT NULL,
                    overview TEXT NOT NULL,
                    releaseDate TEXT NOT NULL,
                    backdropPath TEXT,
                    posterPath TEXT,
                    runtime INTEGER,
                    rating REAL,
                    expiration INTEGER NOT NULL
                )
            """.trimIndent())

            // Copy data
            db.execSQL("INSERT INTO movie_tmp (id, title, overview, releaseDate, backdropPath, posterPath, runtime, rating, expiration) SELECT * FROM movie")

            // Drop Old Table
            db.execSQL("DROP TABLE movie")

            // Rename Copied Table
            db.execSQL("ALTER TABLE movie_tmp RENAME TO movie")
        }
    }
}