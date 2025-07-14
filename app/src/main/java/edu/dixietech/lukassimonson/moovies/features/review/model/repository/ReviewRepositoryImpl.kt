package edu.dixietech.lukassimonson.moovies.features.review.model.repository

import edu.dixietech.lukassimonson.moovies.features.review.model.database.ReviewDao
import edu.dixietech.lukassimonson.moovies.shared.database.MissingItemException
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import edu.dixietech.lukassimonson.moovies.shared.domain.toMovie
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class ReviewRepositoryImpl(
    private val dao: ReviewDao,
    private val context: CoroutineContext
): ReviewRepository {
    override suspend fun getMovieReview(id: Int) = withContext(context) {
        val entity = dao.getMovie(id)
            return@withContext entity?.toMovie() ?: throw MissingItemException()
    }
}