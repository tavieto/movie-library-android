package dev.tavieto.movielibrary.core.commons.exception

data class ListException(
    val listException: ArrayList<Throwable> = arrayListOf()
) : Throwable("We found some errors.")
