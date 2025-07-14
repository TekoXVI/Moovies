package edu.dixietech.lukassimonson.moovies.features.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.dixietech.lukassimonson.moovies.features.search.model.repository.SearchRepository
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVm @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun search(term: String) {
        viewModelScope.launch {
            _movies.postValue(repository.search(term))
        }
    }
}