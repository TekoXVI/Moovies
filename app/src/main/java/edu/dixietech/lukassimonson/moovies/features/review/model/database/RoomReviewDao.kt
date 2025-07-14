package edu.dixietech.lukassimonson.moovies.features.review.model.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieReviewJoin
import edu.dixietech.lukassimonson.moovies.shared.database.entities.ReviewEntity

@Dao
interface RoomReviewDao: ReviewDao {
    @Transaction
    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieAndReview(id: Int): MovieReviewMap?

    @Upsert
    suspend fun save(movie: MovieEntity)

    @Upsert
    suspend fun saveReview(review: ReviewEntity)

    @Upsert
    suspend fun saveJoin(join: MovieReviewJoin)

    override suspend fun getMovie(id: Int): MovieEntity? {
        val map = getMovieAndReview(id)
        return map?.movieEntity?.copy(review = map.reviewEntity)
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        save(movie)
        movie.review
            ?.also { saveReview(it) }
            ?.let { MovieReviewJoin(movie.id, it.id) }
            ?.let { saveJoin(it) }
    }
}