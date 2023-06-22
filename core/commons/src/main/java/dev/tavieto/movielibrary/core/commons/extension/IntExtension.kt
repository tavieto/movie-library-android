package dev.tavieto.movielibrary.core.commons.extension

fun Int.setSizeFormat(): String {
    val localInt = this
    val localString = localInt.toString()
    val firstChar = localString[0].digitToInt()
    val lastChar = localString[localString.length - 1]
    return "${localString.subSequence(0, localString.length - 1)}/" +
            "${if (lastChar != '0') firstChar else firstChar + 1}$lastChar"
}
