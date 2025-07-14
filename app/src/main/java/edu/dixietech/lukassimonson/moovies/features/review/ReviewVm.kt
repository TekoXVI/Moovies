package edu.dixietech.lukassimonson.moovies.features.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.dixietech.lukassimonson.moovies.features.review.model.repository.ReviewRepository
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import edu.dixietech.lukassimonson.moovies.shared.domain.Review
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewVm @Inject constructor(
    private val repo: ReviewRepository
) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun getReview(movieId: Int) {
        viewModelScope.launch {
            _movie.postValue(repo.getMovieReview(movieId))
        }
    }

    fun saveReview(movie: Movie, review: Review) {
        viewModelScope.launch {
            repo.saveMovieReview(movie, review)
            getReview(movie.id)
        }
    }

    fun saveRating(movie: Movie, rating: Double) {
        viewModelScope.launch {
            repo.saveMovieRating(movie, rating)
            getReview(movie.id)
        }
    }
}