package edu.dixietech.lukassimonson.moovies.features.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import edu.dixietech.lukassimonson.moovies.features.search.model.network.KtorSearchNetwork
import edu.dixietech.lukassimonson.moovies.features.search.model.network.SearchNetwork
import edu.dixietech.lukassimonson.moovies.features.search.model.repository.SearchRepository
import edu.dixietech.lukassimonson.moovies.features.search.model.repository.SearchRepositoryImpl
import edu.dixietech.lukassimonson.moovies.shared.network.KtorRoot
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class SearchModule {
    @Provides
    fun providesSearchNetwork(
        root: KtorRoot
    ): SearchNetwork = KtorSearchNetwork(root, Dispatchers.IO)

    @Provides
    fun providesSearchRepository(
        network: SearchNetwork
    ): SearchRepository = SearchRepositoryImpl(
        network = network,
        context = Dispatchers.Default
    )
}