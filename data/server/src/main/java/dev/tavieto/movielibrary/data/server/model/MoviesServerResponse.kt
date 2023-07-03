package dev.tavieto.movielibrary.data.server.model

import dev.tavieto.movielibrary.data.remote.model.MoviesResponse

typealias MoviesServerResponse = List<MovieServerResponse>

internal fun MoviesServerResponse.mapToRemote(isFavorite: Boolean = false): MoviesResponse {
    return this.map { it.mapToRemote(isFavorite) }
}
