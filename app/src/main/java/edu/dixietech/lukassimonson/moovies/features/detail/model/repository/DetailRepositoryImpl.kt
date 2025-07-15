package edu.dixietech.lukassimonson.moovies.features.detail.model.repository

import edu.dixietech.lukassimonson.moovies.features.detail.model.database.DetailDao
import edu.dixietech.lukassimonson.moovies.features.detail.model.network.DetailNetwork
import edu.dixietech.lukassimonson.moovies.features.review.model.database.ReviewDao
import edu.dixietech.lukassimonson.moovies.shared.database.MissingItemException
import edu.dixietech.lukassimonson.moovies.shared.database.entities.toMovieEntity
import edu.dixietech.lukassimonson.moovies.shared.domain.toMovie
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class DetailRepositoryImpl(
    private val network: DetailNetwork,
    private val detailDao: DetailDao,
    private val reviewDao: ReviewDao,
    private val context: CoroutineContext
): DetailRepository {
    override suspend fun getMovieDetails(id: Int) = withContext(context) {
        val entity = reviewDao.getMovie(id)
        if (entity != null && entity.expiration > Clock.System.now().epochSeconds) {
            return@withContext entity.toMovie()
        }

        val dto = network.getDetailsForMovie(id)
        detailDao.saveMovie(dto.toMovieEntity())
        return@withContext detailDao.getMovie(id)?.toMovie() ?: throw MissingItemException()
    }
}