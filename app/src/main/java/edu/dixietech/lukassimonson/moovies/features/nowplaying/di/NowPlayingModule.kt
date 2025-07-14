package edu.dixietech.lukassimonson.moovies.features.nowplaying.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import edu.dixietech.lukassimonson.moovies.features.nowplaying.model.network.KtorNowPlayingNetwork
import edu.dixietech.lukassimonson.moovies.features.nowplaying.model.network.NowPlayingNetwork
import edu.dixietech.lukassimonson.moovies.features.nowplaying.model.repository.NowPlayingRepoImpl
import edu.dixietech.lukassimonson.moovies.features.nowplaying.model.repository.NowPlayingRepository
import edu.dixietech.lukassimonson.moovies.shared.network.KtorRoot
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class NowPlayingModule {
    @Provides
    fun providesNowPlayingNetwork(
        root: KtorRoot
    ): NowPlayingNetwork = KtorNowPlayingNetwork(
        root = root,
        context = Dispatchers.IO
    )

    @Provides
    fun providesNowPlayingRepo(
        network: NowPlayingNetwork
    ): NowPlayingRepository = NowPlayingRepoImpl(
        network = network,
        context = Dispatchers.IO
    )
}