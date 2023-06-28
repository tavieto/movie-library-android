package dev.tavieto.movielibrary.data.server.model

import dev.tavieto.movielibrary.data.remote.model.MoviesResponse

typealias MoviesServerResponse = List<MovieServerResponse>

fun MoviesServerResponse.mapToRemote(): MoviesResponse {
    return this.map { it.mapToRemote() }
}
