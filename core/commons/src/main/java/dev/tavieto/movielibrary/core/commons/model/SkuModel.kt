package dev.tavieto.movielibrary.core.commons.model

import com.raptor.sports.commons.exception.InvalidSkuException

data class SkuModel(
    val master: String,
    val color: String,
    val size: String? = null
) {
    val format
        get() = run {
            val base = "${master}_$color"
            if (size != null) "${base}_$size" else base
        }
}

@Suppress("MagicNumber", "TooGenericExceptionCaught", "SwallowedException")
fun String.getSkuOrNull(): SkuModel? {
    val parts = split("_")

    return try {
        when (parts.size) {
            2 -> SkuModel(
                master = parts[0],
                color = parts[1]
            )
            3 -> SkuModel(
                master = parts[0],
                color = parts[1],
                size = parts[2]
            )
            else -> throw InvalidSkuException()
        }
    } catch (exception: Throwable) {
        null
    }
}
