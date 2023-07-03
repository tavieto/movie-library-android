package dev.tavieto.movielibrary.feature.main.model

import dev.tavieto.movielibrary.domain.movie.model.MovieListDomain

typealias MovieListModel = List<MovieModel>

internal fun MovieListDomain.mapFromDomain(): MovieListModel {
    return this.map { it.mapFromDomain() }
}
