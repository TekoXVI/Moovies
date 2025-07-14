package edu.dixietech.lukassimonson.moovies.features.nowplaying.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.dixietech.lukassimonson.moovies.features.nowplaying.model.repository.NowPlayingRepository
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingVm @Inject constructor(
    private val repo: NowPlayingRepository
): ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun loadNowPlaying() {
        val mov = _movies.value
        if (mov == null || mov.isEmpty()) {
            viewModelScope.launch {
                _movies.postValue(repo.getNowPlaying())
            }
        }
    }
}