package edu.dixietech.lukassimonson.moovies.features.detail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import edu.dixietech.lukassimonson.moovies.features.detail.model.database.DetailDao
import edu.dixietech.lukassimonson.moovies.features.detail.model.network.DetailNetwork
import edu.dixietech.lukassimonson.moovies.features.detail.model.network.KtorDetailNetwork
import edu.dixietech.lukassimonson.moovies.features.detail.model.repository.DetailRepository
import edu.dixietech.lukassimonson.moovies.features.detail.model.repository.DetailRepositoryImpl
import edu.dixietech.lukassimonson.moovies.features.review.model.database.ReviewDao
import edu.dixietech.lukassimonson.moovies.shared.database.MovieDatabase
import edu.dixietech.lukassimonson.moovies.shared.network.KtorRoot
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class DetailModule {
    @Provides
    fun providesDetailDao(
        database: MovieDatabase
    ): DetailDao = database.detailDao()

    @Provides
    fun providesDetailNetwork(
        root: KtorRoot
    ): DetailNetwork = KtorDetailNetwork(
        root = root,
        context = Dispatchers.IO
    )

    @Provides
    fun providesDetailRepository(
        network: DetailNetwork,
        detailDao: DetailDao,
        reviewDao: ReviewDao
    ): DetailRepository = DetailRepositoryImpl(
        network = network,
        detailDao = detailDao,
        reviewDao = reviewDao,
        context = Dispatchers.Default
    )
}