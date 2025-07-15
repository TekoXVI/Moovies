package edu.dixietech.lukassimonson.moovies.features.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import edu.dixietech.lukassimonson.moovies.R
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
        viewModel.getMovie(args.movieId)

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
                    text = "Overall Rating: ${movie?.rating}/10"
                )
                if (review != null) {
                    Text(
                        text = "Your Rating:",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        (0..review.rating-1).forEach { _ ->
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Star Icon",
                                tint = androidx.compose.ui.graphics.Color.Yellow
                            )
                        }
                        (0..(4-review.rating)).forEach { _ ->
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = "Outlined Star Icon"
                            )
                        }
                    }
                    Text(
                        text = review.title,
                        fontSize = 20.sp
                    )
                    Text(
                        text = review.body,
                        fontSize = 16.sp
                    )


                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        findNavController().navigate(
                            DetailFragmentDirections.actionDetailFragmentToReviewFragment(
                                movie?.id ?: 0
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (review != null) {
                        Text(
                            text = "Edit Review",
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "Add Review",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}