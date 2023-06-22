package dev.tavieto.movielibrary.core.commons.extension

fun Any?.isNotNull(): Boolean {
    return this?.let { true } ?: kotlin.run { false }
}
