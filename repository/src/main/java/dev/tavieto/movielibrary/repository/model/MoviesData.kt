package dev.tavieto.movielibrary.repository.model

import dev.tavieto.movielibrary.domain.movie.model.MovieListDomain

typealias MoviesData = List<MovieData>

internal fun MoviesData.mapToDomain(): MovieListDomain {
    return this.map { it.mapToDomain() }
}