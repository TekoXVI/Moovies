package edu.dixietech.lukassimonson.moovies.features.review.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import edu.dixietech.lukassimonson.moovies.databinding.FragmentReviewBinding
import edu.dixietech.lukassimonson.moovies.features.review.ReviewVm
import edu.dixietech.lukassimonson.moovies.shared.domain.Review
import edu.dixietech.lukassimonson.moovies.shared.ui.MooviesTheme
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavController
import androidx.navigation.findNavController

@AndroidEntryPoint
class ReviewFragment: Fragment() {
    private lateinit var binding: FragmentReviewBinding
    private lateinit var viewModel: ReviewVm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ReviewVm::class]

        val args = ReviewFragmentArgs.fromBundle(requireArguments())
        viewModel.getReview(args.movieId)

        return binding.root.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MooviesTheme {
                    Surface {
                        ReviewScreen(
                            viewModel,
                            findNavController()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewScreen(
    viewModel: ReviewVm,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        var rating by remember { mutableIntStateOf(0) }
        var titleText by remember { mutableStateOf("") }
        var bodyText by remember { mutableStateOf("") }

        Text(
            text = "Review",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            IconButton(
                onClick = { rating = 1 }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Filled Star",
                    tint = if (rating >= 1) Color.Yellow else Color.Gray
                )
            }
            IconButton(
                onClick = { rating = 2 }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Filled Star",
                    tint = if (rating >= 2) Color.Yellow else Color.Gray
                )
            }
            IconButton(
                onClick = { rating = 3 }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Filled Star",
                    tint = if (rating >= 3) Color.Yellow else Color.Gray
                )
            }
            IconButton(
                onClick = { rating = 4 }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Filled Star",
                    tint = if (rating >= 4) Color.Yellow else Color.Gray
                )
            }
            IconButton(
                onClick = { rating = 5 }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Filled Star",
                    tint = if (rating >= 5) Color.Yellow else Color.Gray
                )
            }
        }
        TextField(
            value = titleText,
            onValueChange = { titleText = it },
            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
        )
        TextField(
            value = bodyText,
            onValueChange = { bodyText = it },
            label = { Text("Body") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.saveReview(
                    movie = viewModel.movie.value!!,
                    review = Review(
                        id = viewModel.movie.value?.id ?: 0,
                        rating = rating,
                        title = titleText,
                        body = bodyText
                    )
                )
                navController.popBackStack()
            },
            enabled = rating > 0
                    && titleText.isNotBlank()
                    && bodyText.isNotBlank()
        ) {
            Text(
                text = "Submit",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}