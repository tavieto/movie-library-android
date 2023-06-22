package dev.tavieto.movielibrary.core.extension

@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun <T> List<T>.toInfinity(): List<T> {
    return try {
        val newList = mutableListOf<T>()
        newList.add(this.last())
        newList.addAll(elements = this)
        newList.add(this.first())
        newList
    } catch (e: Exception) {
        this
    }
}

fun <T> List<T>.toInfinityCarousel(): List<T> {
    val newList = mutableListOf<T>()
    newList.add(this.last())
    newList.addAll(this)
    newList.removeLast()
    return newList
}

// D A B c d
