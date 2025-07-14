package edu.dixietech.lukassimonson.moovies.features.detail.model.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import edu.dixietech.lukassimonson.moovies.shared.database.entities.GenreEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieGenreJoin
import kotlin.time.ExperimentalTime

@Dao
interface RoomDetailDao: DetailDao {

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id LIMIT 1")
    suspend fun getMovieAndGenres(id: Int): MovieGenreMap?

    @Upsert
    suspend fun save(movie: MovieEntity)

    @Upsert
    suspend fun saveGenres(genre: List<GenreEntity>)

    @Upsert
    suspend fun saveJoins(joins: List<MovieGenreJoin>)

    @OptIn(ExperimentalTime::class)
    override suspend fun getMovie(id: Int): MovieEntity? {
        val map = getMovieAndGenres(id)
        return map?.movieEntity?.copy(genres = map.genreEntities)
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        save(movie) // Save Movie
        movie.genres
            ?.also { saveGenres(it) } // Save Genres
            ?.map { MovieGenreJoin(movie.id, it.id) }
            ?.let { saveJoins(it) }  // Save Joins
    }
}