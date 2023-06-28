package dev.tavieto.movielibrary.repository.model

import dev.tavieto.movielibrary.domain.movie.model.MoviesDomain

typealias MoviesData = List<MovieData>

internal fun MoviesData.mapToDomain(): MoviesDomain {
    return this.map { it.mapToDomain() }
}
