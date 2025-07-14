package edu.dixietech.lukassimonson.moovies.features.review.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import edu.dixietech.lukassimonson.moovies.features.review.model.database.ReviewDao
import edu.dixietech.lukassimonson.moovies.features.review.model.repository.ReviewRepository
import edu.dixietech.lukassimonson.moovies.features.review.model.repository.ReviewRepositoryImpl
import edu.dixietech.lukassimonson.moovies.shared.database.MovieDatabase
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class ReviewModule {
    @Provides
    fun providesReviewDao(
        database: MovieDatabase
    ): ReviewDao = database.reviewDao()

    @Provides
    fun providesReviewRepository(
        dao: ReviewDao
    ): ReviewRepository = ReviewRepositoryImpl(
        dao = dao,
        context = Dispatchers.Default
    )
}