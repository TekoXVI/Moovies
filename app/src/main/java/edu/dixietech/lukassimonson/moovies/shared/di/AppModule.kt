package edu.dixietech.lukassimonson.moovies.shared.di

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.dixietech.lukassimonson.moovies.shared.database.Migrations
import edu.dixietech.lukassimonson.moovies.shared.database.MovieDatabase
import edu.dixietech.lukassimonson.moovies.shared.database.RoomMovieDatabase
import edu.dixietech.lukassimonson.moovies.shared.network.KtorRoot
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesKtorRoot(): KtorRoot =
        KtorRoot(
            baseUrl = "https://api.themoviedb.org",
            token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZDFhMzc1ODZlZWUyYmJjM2ZhMTEwN2UzMjYyMDAwZSIsIm5iZiI6MTYxNzgxMzcxNS41MTYsInN1YiI6IjYwNmRlMGQzOWNhNzU5MDA1Nzg4ZGY4NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xRJpoTsb7o41_mtCr9ZNUmb4nrixNWTD5gNoA-pMvxk"
        )

    @Provides
    @Singleton
    fun providesRoomDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase = Room.databaseBuilder(
        context,
        RoomMovieDatabase::class.java,
        "movie_database"
    )
        .addMigrations(Migrations.OneToTwo())
        .build()
}