package edu.dixietech.lukassimonson.moovies.features.nowplaying.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.dixietech.lukassimonson.moovies.databinding.FragmentNowPlayingBinding
import edu.dixietech.lukassimonson.moovies.features.nowplaying.viewmodel.NowPlayingVm
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import edu.dixietech.lukassimonson.moovies.shared.ui.MovieAdapter

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private val adapter = MovieAdapter(this::navigateToDetail)
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var binding: FragmentNowPlayingBinding
    private lateinit var viewModel: NowPlayingVm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NowPlayingVm::class]
        layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecycler()

        viewModel.loadNowPlaying()
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }

        binding.fab.setOnClickListener {
            findNavController()
                .navigate(NowPlayingFragmentDirections.actionNowPlayingFragmentToSearchFragment())
        }
    }

    private fun setupRecycler() {
        ViewCompat.setNestedScrollingEnabled(binding.nowPlayingRecycler, false)
        binding.nowPlayingRecycler.adapter = adapter
        binding.nowPlayingRecycler.layoutManager = layoutManager
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController()
            .navigate(
                NowPlayingFragmentDirections
                    .actionNowPlayingFragmentToDetailFragment(movie.id)
            )
    }
}