package edu.dixietech.lukassimonson.moovies.features.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import edu.dixietech.lukassimonson.moovies.databinding.FragmentDetailBinding
import edu.dixietech.lukassimonson.moovies.features.detail.viewmodel.DetailVm
import edu.dixietech.lukassimonson.moovies.shared.domain.Genre
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.sp
import edu.dixietech.lukassimonson.moovies.shared.ui.MooviesTheme

@AndroidEntryPoint
class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailVm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DetailVm::class]

        setupObservers()

        val args = DetailFragmentArgs.fromBundle(requireArguments())
        viewModel.getMovie(args.movieId)

        binding.reviewSection.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MooviesTheme {
                    Surface {
                        MovieReview(viewModel)
                    }
                }
            }
        }

        return binding.root
    }

    private fun setupObservers() {
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.movie = movie

            if (movie.backdropPath != null) {
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
                    .into(binding.backdropImage)
            }

            movie.genres?.forEach { genre ->
                binding.genreLayout.addView(createGenreCard(genre))
            }
        }
    }

    private fun createGenreCard(genre: Genre): View {
        val context = requireContext()
        return MaterialCardView(context).apply {
            val params = ViewGroup.MarginLayoutParams(binding.genreLayout.layoutParams)
            params.setMargins(10)
            layoutParams = params

            addView(
                TextView(context).apply {
                    text = genre.name
                    setPadding(15)
                }
            )
        }
    }

    @Composable
    fun MovieReview(
        viewModel: DetailVm,
        modifier: Modifier = Modifier
    ) {
        val movie by viewModel.movie.observeAsState()

        movie?.review.let { review ->

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
            ) {
                Text(
                    text = "Rating: ${movie?.rating}"
                )
                if (review != null) {
                    Text(
                        text = review.title,
                        fontSize = 24.sp,

                        )
                    Text(
                        text = review.body,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}