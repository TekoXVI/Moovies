package edu.dixietech.lukassimonson.moovies.shared.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.dixietech.lukassimonson.moovies.R
import edu.dixietech.lukassimonson.moovies.databinding.ListItemNowPlayingBinding
import edu.dixietech.lukassimonson.moovies.shared.domain.Movie

class MovieAdapter(
    val onMovieClick: (Movie) -> Unit
): ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onMovieClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemNowPlayingBinding,
        private val onClick: (Movie) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.movie = item
            binding.root.setOnClickListener { onClick(item) }
            if (item.posterPath != null) {
                Glide.with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w342${item.posterPath}")
                    .into(binding.posterImage)
            } else {
                binding.posterImage.setImageDrawable(
                    AppCompatResources
                        .getDrawable(binding.root.context, R.drawable.ic_launcher)
                )
            }
        }

        companion object {
            fun from(parent: ViewGroup, onClick: (Movie) -> Unit): ViewHolder {
                return LayoutInflater
                    .from(parent.context)
                    .run { ViewHolder(ListItemNowPlayingBinding.inflate(this, parent, false), onClick) }
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}