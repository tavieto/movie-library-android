package dev.tavieto.movielibrary.core.commons.extension

import dev.tavieto.movielibrary.core.commons.enums.RegexEnum
import java.text.SimpleDateFormat
import java.util.Locale

private const val MIN_CHAR = 8

fun String.isFullName() = RegexEnum.FULL_NAME.match(this)
fun String.isNotFullName() = isFullName().not()
fun String.isEmail() = RegexEnum.EMAIL.match(this)
fun String.isNotEmail() = isEmail().not()

fun String.isPassword() = RegexEnum.PASSWORD.match(this)
fun String.isNotPassword() = isPassword().not()
fun String.hasLetters() = RegexEnum.PASSWORD_LETTERS.match(this)
fun String.hasNotLetters() = hasLetters().not()

fun String.hasUppercase() = RegexEnum.PASSWORD_UPPERCASE.match(this)
fun String.hasNotUppercase() = hasUppercase().not()

fun String.hasLowercase() = RegexEnum.PASSWORD_LOWERCASE.match(this)
fun String.hasNotLowercase() = hasLowercase().not()
fun String.hasNumbers() = RegexEnum.PASSWORD_NUMBERS.match(this)
fun String.hasNotNumbers() = hasNumbers().not()
fun String.hasSpecialChars() = RegexEnum.PASSWORD_SPECIAL_CHARACTERS.match(this)
fun String.hasNotSpecialChars() = hasSpecialChars().not()
fun String.hasMinCharPassword() = this.length >= MIN_CHAR
fun String.hasNotMinCharPassword() = hasMinCharPassword().not()
fun String.hasMinLength(minLength: Int = 8) = this.length >= minLength
fun String.hasNotMinLength(minLength: Int = 8) = this.hasMinLength(minLength).not()

fun String.setMask(mask: String): String {
    try {
        var newText = ""
        if (isNotEmpty()) {
            val listMask = mutableListOf<Int>()
            val newTextList = mask.toMutableList()
            mask.forEachIndexed { index, c ->
                if (c == '#') {
                    listMask.add(index)
                }
            }
            listMask.forEachIndexed { index, position ->
                if (length - 1 >= index) {
                    newTextList.removeAt(position)
                    newTextList.add(index = position, element = this[index])
                }
            }
            newText = ""
            newTextList.forEachIndexed { index, c ->
                if (index <= listMask[length - 1]) {
                    newText += c
                }
            }
        }
        return newText
    } catch (e: Exception) {
        return this
    }
}

fun String.clearMask(mask: String): String {
    try {
        val listMask = mutableListOf<Int>()
        val newCharList = this.toMutableList()
        val indexToRemove = mutableListOf<Int>()
        mask.forEachIndexed { index, c ->
            if (c == '#') {
                listMask.add(index)
            } else {
                indexToRemove.add(index)
            }
        }

        indexToRemove.reversed().forEachIndexed { _, position ->
            if (length - 1 >= position) {
                newCharList.removeAt(position)
            }
        }

        var newText = ""
        newCharList.forEachIndexed { _, c ->
            newText += c
        }

        return newText
    } catch (e: Exception) {
        return this
    }
}

// TODO("WIP - I have to done this extension to convert date formats.")
fun String.formatDate(pattern: String = "yyyy-MM-dd", to: String = "dd/MM/yyyy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val date = dateFormat.parse(this)
    val stringDateFormat = SimpleDateFormat(to, Locale.getDefault())
    return stringDateFormat.format(date)
}
