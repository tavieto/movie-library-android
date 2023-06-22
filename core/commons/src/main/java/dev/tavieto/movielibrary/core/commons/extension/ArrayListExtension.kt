package dev.tavieto.movielibrary.core.commons.extension

inline fun <T> ArrayList<T>.sumOf(selector: (T) -> Float?): Float {
    var sum = 0f
    forEach { element ->
        sum += selector(element) ?: 0f
    }
    return sum
}
