package dev.tavieto.movielibrary.data.remote.model

import dev.tavieto.movielibrary.repository.model.MoviesData

typealias MoviesResponse = List<MovieResponse>

fun MoviesResponse.mapToRepository(): MoviesData {
    return this.map { it.mapToRepository() }
}
