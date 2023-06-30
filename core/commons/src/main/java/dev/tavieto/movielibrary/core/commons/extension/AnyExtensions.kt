package dev.tavieto.movielibrary.core.commons.extension

fun Any?.isNull(): Boolean = this == null
fun Any?.isNotNull(): Boolean = isNull().not()
