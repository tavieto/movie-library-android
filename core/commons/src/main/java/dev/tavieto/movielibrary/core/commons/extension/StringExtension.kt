@file:Suppress("TooManyFunctions", "SwallowedException", "TooGenericExceptionCaught")

package dev.tavieto.movielibrary.core.commons.extension

import com.raptor.sports.commons.enums.RegexEnum
import java.io.File
import java.net.URLDecoder
import java.net.URLEncoder
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.InputMismatchException
import java.util.Locale
import okhttp3.MediaType
import okhttp3.RequestBody

private const val MAX_AGE = 90
private const val MIN_AGE = 18
private const val MIN_CHAR = 8
private const val CPF_LENGTH = 11
private const val DATE_LENGTH = 10

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

val String.Companion.Empty: String
    inline get() = ""

fun CharSequence.unaccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UNACCENT.replace(temp, String.Empty)
}

fun String.dotToComma() = this.reversed().replaceFirst('.', ',').reversed()
fun String.removeNotNumbers(): String = this.replace("[^\\d]".toRegex(), String.Empty)
fun String.match(value: String) = this == value
fun String.notMatch(value: String) = match(value).not()

fun String.isFullName() = RegexEnum.FULL_NAME.match(this)
fun String.isNotFullName() = isFullName().not()
fun String.extractPresentationName(): String {
    var presentationName = String.Empty
    val listNames: List<String> = this.split(' ')
    presentationName += listNames.first()
    if (listNames.size > 1) {
        presentationName += " ${listNames.last()}"
    }
    return presentationName
}

fun String.isEmail() = RegexEnum.EMAIL.match(this)
fun String.isNotEmail() = isEmail().not()

fun String.isAddressNumber() = RegexEnum.ADDRESS_NUMBER.match(this)
fun String.isNotAddressNumber() = isAddressNumber().not()

fun String.isPassword() = RegexEnum.PASSWORD.match(this)
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
fun String.isNotPassword() = isPassword().not()

fun String.isPhoneNumberSpecialChars() = RegexEnum.PHONE_NUMBER_SPECIAL_CHARS.match(this)
fun String.isNotPhoneNumberSpecialChars() = isPhoneNumberSpecialChars().not()

fun String.isPhoneNumber() = RegexEnum.PHONE_NUMBER.match(this)
fun String.isNotPhoneNumber() = isPhoneNumber().not()

fun String.hasMinLength(minLength: Int = 5) = this.length >= minLength
fun String.hasNotMinLength(minLength: Int = 5) = this.hasMinLength(minLength).not()

fun String.isCep() = RegexEnum.CEP.match(this)
fun String.isNotCep() = isCep().not()
fun String.isCepRegistration() = isBlank() || isCep()
fun String.isNotCepRegistration() = isCepRegistration().not()

fun String.setMask(mask: String): String {
    try {
        var newText = String.Empty
        if (length > 0) {
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
            newText = String.Empty
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

        var newText = String.Empty
        newCharList.forEachIndexed { _, c ->
            newText += c
        }

        return newText
    } catch (e: Exception) {
        return this
    }
}

@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun String.isDate(format: String = "dd/MM/yyyy"): Boolean {
    if (this.length < DATE_LENGTH) return false
    return try {
        SimpleDateFormat(format, Locale.getDefault()).also {
            it.isLenient = false
            it.parse(this)
        }
        RegexEnum.DATE.value.matches(this)
    } catch (e: Exception) {
        false
    }
}

fun String.isNotDate(format: String = "dd/MM/yyyy") = isDate(format).not()

fun String.isBirthDate(format: String = "dd/MM/yyyy"): Boolean {
    if (this.length < DATE_LENGTH) return false
    return try {
        SimpleDateFormat(format, Locale.getDefault()).apply {
            isLenient = false
            parse(this@isBirthDate)
        }
        true
    } catch (e: Exception) {
        false
    }
}

fun String.isNotMinAgeValid(format: String = "dd/MM/yyyy"): Boolean {
    return try {
        this.toDate(format).after(Date().subtractYears(MIN_AGE))
    } catch (e: Throwable) {
        false
    }
}

fun String.isNotMaxAgeValid(format: String = "dd/MM/yyyy"): Boolean {
    return try {
        this.toDate(format).before(Date().subtractYears(MAX_AGE))
    } catch (e: Throwable) {
        false
    }
}

fun String.isNotBirthDate(format: String = "dd/MM/yyyy") = isBirthDate(format).not()

fun String.isValidFirstName() = RegexEnum.FIRST_NAME.match(this)
fun String.isNotValidFirstName() = isValidFirstName().not()

fun String.isValidLastName() = RegexEnum.LAST_NAME.match(this)
fun String.isNotValidLastName() = isValidLastName().not()

fun String.isNotCpf() = isCpf().not()

@Suppress("MagicNumber", "ReturnCount", "SwallowedException")
fun String.isCpf(): Boolean {
    val cpf = this.removeNotNumbers()
    if (cpf.length != CPF_LENGTH) return false

    var allDigitsEqual = true
    cpf.forEach {
        if (cpf.first() != it) {
            allDigitsEqual = false
        }
    }
    if (allDigitsEqual) return false

    return try {
        val weight = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
        val cpfSub = cpf.substring(0, 9)
        val digit1 = cpfSub.validateCpfDigit(weight)
        val digit2 = (cpfSub + digit1).validateCpfDigit(weight)

        cpf == cpfSub.plus(digit1).plus(digit2)
    } catch (error: InputMismatchException) {
        false
    }
}

@Suppress("MagicNumber", "TooGenericExceptionCaught", "SwallowedException")
private fun String.validateCpfDigit(weight: IntArray): Int {
    try {
        var valid = 0
        var index = this.length - 1
        var digit: Int

        while (index >= 0) {
            digit = this.substring(index, index + 1).toInt()
            valid += digit * weight[weight.size - this.length + index]
            index--
        }

        valid = 11 - valid % 11

        return if (valid > 9) {
            0
        } else {
            valid
        }
    } catch (e: Exception) {
        return -1
    }
}

fun String.ifNotBlank(callback: () -> Unit) {
    if (this.isNotBlank()) callback()
}

fun String.encode() = URLEncoder.encode(this, "UTF-8")
fun String.decode() = URLDecoder.decode(this, "UTF-8")

fun String?.toFootSize(): String? {
    return try {
        if (this == null) {
            throw NullPointerException()
        } else {
            toCharArray()[0].toString().plus(
            this.toCharArray()[1].toString()
        ).plus(
            this.toCharArray().last().toString()
        )
        }
    } catch (e: Exception) {
        null
    }
}

private const val MINIMUM_SHOE_SIZE_LENGTH = 3

fun String.fromShoesSize(): String {
    return when (length) {
        MINIMUM_SHOE_SIZE_LENGTH -> {
            val sizeText = this
            var sizeFormatted = sizeText.substring(0..1)
            var firstNumber: Int = sizeText.first().digitToInt()
            if (sizeText.last() == '0') {
                firstNumber++
            }
            sizeFormatted += "/$firstNumber${sizeText.last()}"
            return sizeFormatted
        }
        else -> this
    }
}

fun String.toRequestBody(): RequestBody =
    RequestBody.create(MediaType.parse("text/plain"), this)

fun String?.toFile() = File(this ?: String())

@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun String.formatDate(from: String = "yyyy-MM-dd", to: String = "dd/MM/yyyy"): String = try {
    val fromFormat = SimpleDateFormat(from, Locale.getDefault()).also { it.isLenient = false }
    val date = fromFormat.parse(this)
    val formatTo = SimpleDateFormat(to, Locale.getDefault())
    formatTo.format(date)
} catch (e: Exception) {
    this
}

@Suppress("MagicNumber")
fun String.extractDate(): String {
    val date = this
    val isValid = date[4] == '-' && date[7] == '-'

    if (isValid.not()) return String()

    val year = date.substring(range = 0..3)
    val month = date.substring(range = 5..6)
    val day = date.substring(range = 8..9)

    return "$day/$month/$year"
}

fun String.capitalize(): String {
    try {
        var newText = this.lowercase()
            .replaceRange(startIndex = 0, endIndex = 1, replacement = this[0].uppercase())
        val spacePositions = mutableListOf<Int>()
        this.forEachIndexed { index, c ->
            if (c == ' ') {
                spacePositions.add(index)
            }
        }

        spacePositions.forEachIndexed { index, i ->
            if (index != newText.lastIndex) {
                newText = newText.replaceRange(
                    startIndex = i + 1,
                    endIndex = i + 2,
                    replacement = newText[i + 1].uppercase()
                )
            }
        }
        return newText
    } catch (e: Exception) {
        return this
    }
}
