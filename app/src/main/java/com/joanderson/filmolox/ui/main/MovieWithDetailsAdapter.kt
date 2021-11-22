package com.joanderson.filmolox.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.joanderson.filmolox.R
import com.joanderson.filmolox.data.model.Movie
import com.joanderson.filmolox.databinding.AdapterMovieWithDetailsBinding
import com.squareup.picasso.Picasso


class MovieWithDetailsAdapter : RecyclerView.Adapter<MovieWithDetailsAdapter.MovieViewHolder>() {

    private var movies = emptyList<Movie>()

    class MovieViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val binding = AdapterMovieWithDetailsBinding.bind(itemView)

        fun bindView(movie: Movie) {

            with(binding) {
                tvMovieTitle.text = movie.title
                tvDataLancamento.text = movie.releaseDate
                tvRateAverage.text = movie.voteAverage.toString()
            }

            //Adding genre list
            movie.genres.forEach {
                LayoutInflater.from(context).inflate(
                    R.layout.chip_genre,
                    binding.cgGenres
                )
                val newGenre = binding.cgGenres[binding.cgGenres.size - 1] as Chip
                newGenre.text = it
            }

            Picasso.get()
                .load(movie.posterUrl)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivMovieImage)

        }
    }

    internal fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_movie_with_details, parent, false)
        return MovieViewHolder(itemView, parent.context)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}