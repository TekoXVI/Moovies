package edu.dixietech.lukassimonson.moovies.features.search.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.dixietech.lukassimonson.moovies.databinding.FragmentSearchBinding
import edu.dixietech.lukassimonson.moovies.features.search.viewmodel.SearchVm
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie
import edu.dixietech.lukassimonson.moovies.shared.ui.MovieAdapter

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private val adapter = MovieAdapter(this::navigateToDetail)
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchVm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SearchVm::class]

        setupRecycler()
        setupObservers()

        return binding.root
    }

    private fun setupRecycler() {
        binding.searchRecycler.adapter = adapter
        binding.searchRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.editText.setOnEditorActionListener { v, actionId, e ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(v.text.toString())
                true
            } else { false }
        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController()
            .navigate(
                SearchFragmentDirections
                    .actionSearchFragmentToDetailFragment(movie.id)
            )
    }
}