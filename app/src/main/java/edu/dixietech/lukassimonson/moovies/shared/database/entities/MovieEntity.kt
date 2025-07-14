package edu.dixietech.lukassimonson.moovies.shared.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.GenreDto
import edu.dixietech.lukassimonson.moovies.shared.network.dtos.MovieDto
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@Entity("movie")
@OptIn(ExperimentalTime::class)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val backdropPath: String?,
    val posterPath: String?,
    val runtime: Int?,
    val rating: Double?,

    val expiration: Long,

    @Ignore
    val genres: List<GenreEntity>? = null,

    @Ignore
    val review: ReviewEntity? = null
) {

    // Allows @Ignore genres property
    constructor(
        id: Int,
        title: String,
        overview: String,
        releaseDate: String,
        backdropPath: String,
        posterPath: String,
        runtime: Int?,
        rating: Double?,
        expiration: Long
    ): this(id, title, overview, releaseDate, backdropPath, posterPath, runtime, rating, expiration, genres = null, review = null)
}

@OptIn(ExperimentalTime::class)
fun MovieDto.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    backdropPath = backdropPath,
    posterPath = posterPath,
    runtime = runtime,
    rating = rating,
    expiration = Clock.System.now().plus(86_400.seconds).epochSeconds, // 1 Day
    genres = genres?.map(GenreDto::toGenreEntity)
)