package com.joanderson.filmolox.data.model

data class Movie(
    val id: Int,
    val voteAverage: Double,
    val title: String,
    val posterUrl: String,
    val genres: List<String>,
    val releaseDate: String
)
