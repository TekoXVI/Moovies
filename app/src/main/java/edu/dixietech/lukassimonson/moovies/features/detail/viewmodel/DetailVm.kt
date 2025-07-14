package edu.dixietech.lukassimonson.moovies.features.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.dixietech.lukassimonson.moovies.features.detail.model.repository.DetailRepository
import edu.dixietech.lukassimonson.moovies.features.review.model.repository.ReviewRepository
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVm @Inject constructor(
    private val detailRepo: DetailRepository,
    private val reviewRepo: ReviewRepository
): ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun getMovie(id: Int) {
        viewModelScope.launch {
            _movie.postValue(detailRepo.getMovieDetails(id))
            _movie.postValue(reviewRepo.getMovieReview(id))
        }
    }
}