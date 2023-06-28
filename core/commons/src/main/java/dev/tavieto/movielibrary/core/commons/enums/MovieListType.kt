package dev.tavieto.movielibrary.core.commons.enums

enum class MovieListType(val id: String) {
    NOW_PLAYING(id = "now_playing"),
    POPULAR(id = "popular"),
    TOP_RATED(id = "top_rated"),
    UPCOMING(id = "upcoming");
}
