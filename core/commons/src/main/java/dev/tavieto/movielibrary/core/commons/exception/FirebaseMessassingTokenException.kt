package dev.tavieto.movielibrary.core.commons.exception

import dev.tavieto.movielibrary.core.commons.base.CodeThrowable

class FirebaseMessassingTokenException : CodeThrowable() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FirebaseMessassingTokenException) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
