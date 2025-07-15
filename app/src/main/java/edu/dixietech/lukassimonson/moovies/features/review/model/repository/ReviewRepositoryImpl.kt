package edu.dixietech.lukassimonson.moovies.features.review.model.repository

import edu.dixietech.lukassimonson.moovies.features.review.model.database.ReviewDao
import edu.dixietech.lukassimonson.moovies.shared.database.MissingItemException
import edu.dixietech.lukassimonson.moovies.shared.database.entities.MovieReviewJoin
import edu.dixietech.lukassimonson.moovies.shared.database.entities.toMovieEntity
import edu.dixietech.lukassimonson.moovies.shared.database.entities.toReviewEntity
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import edu.dixietech.lukassimonson.moovies.shared.domain.Review
import edu.dixietech.lukassimonson.moovies.shared.domain.toMovie
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ReviewRepositoryImpl(
    private val dao: ReviewDao,
    private val context: CoroutineContext
): ReviewRepository {
    override suspend fun getMovieReview(id: Int) = withContext(context) {
        val entity = dao.getMovie(id)
        return@withContext entity?.toMovie() ?: throw MissingItemException()
    }

    override suspend fun saveMovieReview(movie: Movie, review: Review) {
        dao.saveMovie(movie.toMovieEntity())

        dao.saveReview(review.toReviewEntity())
        dao.saveJoin(MovieReviewJoin(movie.id, review.id))

//        val newMovie = movie.copy(review = review)
//        dao.saveMovie(newMovie.toMovieEntity())
    }
}