package dev.tavieto.movielibrary.commons.extension

import dev.tavieto.movielibrary.core.commons.extension.fromShoesSize
import dev.tavieto.movielibrary.core.commons.extension.hasLetters
import dev.tavieto.movielibrary.core.commons.extension.hasMinCharPassword
import dev.tavieto.movielibrary.core.commons.extension.hasNotLetters
import dev.tavieto.movielibrary.core.commons.extension.hasNotMinCharPassword
import dev.tavieto.movielibrary.core.commons.extension.hasNotNumbers
import dev.tavieto.movielibrary.core.commons.extension.hasNotSpecialChars
import dev.tavieto.movielibrary.core.commons.extension.hasNumbers
import dev.tavieto.movielibrary.core.commons.extension.hasSpecialChars
import dev.tavieto.movielibrary.core.commons.extension.isBirthDate
import dev.tavieto.movielibrary.core.commons.extension.isCep
import dev.tavieto.movielibrary.core.commons.extension.isCepRegistration
import dev.tavieto.movielibrary.core.commons.extension.isCpf
import dev.tavieto.movielibrary.core.commons.extension.isDate
import dev.tavieto.movielibrary.core.commons.extension.isEmail
import dev.tavieto.movielibrary.core.commons.extension.isNotBirthDate
import dev.tavieto.movielibrary.core.commons.extension.isNotCep
import dev.tavieto.movielibrary.core.commons.extension.isNotCepRegistration
import dev.tavieto.movielibrary.core.commons.extension.isNotCpf
import dev.tavieto.movielibrary.core.commons.extension.isNotDate
import dev.tavieto.movielibrary.core.commons.extension.isNotEmail
import dev.tavieto.movielibrary.core.commons.extension.isNotPassword
import dev.tavieto.movielibrary.core.commons.extension.isNotPhoneNumber
import dev.tavieto.movielibrary.core.commons.extension.isNotPhoneNumberSpecialChars
import dev.tavieto.movielibrary.core.commons.extension.isNotValidFirstName
import dev.tavieto.movielibrary.core.commons.extension.isNotValidLastName
import dev.tavieto.movielibrary.core.commons.extension.isPassword
import dev.tavieto.movielibrary.core.commons.extension.isPhoneNumber
import dev.tavieto.movielibrary.core.commons.extension.isPhoneNumberSpecialChars
import dev.tavieto.movielibrary.core.commons.extension.isValidFirstName
import dev.tavieto.movielibrary.core.commons.extension.isValidLastName
import dev.tavieto.movielibrary.core.commons.extension.removeNotNumbers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `WHEN has alphanumeric string MUST return just the numbers`() {
        assertEquals("a1b2c3d4".removeNotNumbers(), "1234")
    }

    @Test
    fun `WHEN has a e-mail MUST assert if is valid or invalid`() {
        assertTrue("a@a.com".isEmail())
        assertTrue("test@a.com.br".isEmail())
        assertTrue("a@@a.com".isNotEmail())
        assertTrue("a@@a.com".isNotEmail())
        assertTrue("a@@a.com.".isNotEmail())
        assertTrue("a@".isNotEmail())
        assertTrue("a@a.c".isNotEmail())
    }

    @Test
    fun `WHEN email is valid MUST not contains special characters`() {
        val validEmail = "validemail@email.com"
        assertTrue(validEmail.isEmail())
    }

    @Test
    fun `WHEN email is invalid MUST contains special characters`() {
        val specialCharList = "áÁàÀâÂäÄãÃåÅæÆçÇéÉèÈêÊëËíÍìÌîÎïÏñÑóÓòÒôÔöÖõÕøØœŒßúÚùÙûÛüÜ"
        val invalidEmail = "invalid${specialCharList}email@email.com"
        assertTrue(invalidEmail.isNotEmail())
    }

    @Test
    fun `WHEN has a password MUST assert if is valid or invalid`() {
        assertTrue("A1@998*b".isPassword())
        assertTrue("1a@998*b".isPassword())
        assertTrue("1234567*".isNotPassword())
        assertTrue("a1234567".isNotPassword())
    }

    @Test
    fun `WHEN has a phone with special char number MUST assert if is valid or invalid`() {
        assertTrue("(31)99152-5689".isPhoneNumberSpecialChars())
        assertTrue("(31)09152-5689".isNotPhoneNumberSpecialChars())
        assertTrue("(31)9152-5689".isNotPhoneNumberSpecialChars())
        assertTrue("(01)99152-5689".isNotPhoneNumberSpecialChars())
    }

    @Test
    fun `WHEN has a phone number MUST assert if is valid or invalid`() {
        assertTrue("31991525689".isPhoneNumber())
        assertTrue("31091525689".isNotPhoneNumber())
        assertTrue("319152-5689".isNotPhoneNumber())
        assertTrue("0199152-5689".isNotPhoneNumber())
    }

    @Test
    fun `WHEN has a CEP number MUST assert if is valid or invalid`() {
        assertTrue("010000599".isNotCep())
        assertTrue("393900000".isNotCep())
        assertTrue("00100000".isCep())
        assertTrue("0010000000".isNotCep())
    }

    @Test
    fun `WHEN has a date String MUST assert if is valid or invalid`() {
        assertTrue("29/07/1995".isDate())
        assertTrue("09/07/1995".isDate())
        assertTrue("29/7/1995".isNotDate())
        assertTrue("9/07/19".isNotDate())
        assertTrue("09/07/2".isNotDate())
        assertTrue("09/07/20".isNotDate())
        assertTrue("09/07/202".isNotDate())
    }

    // TODO - FAKE POSITIVE
    @Test
    fun `WHEN has a birthday MUST assert if is valid or invalid`() {
        assertTrue("07/07/1920".isBirthDate())
        assertTrue("32/07/1900".isNotBirthDate())
    }

    @Test
    fun `WHEN has a first name String MUST assert if is valid or invalid`() {
        assertTrue("João Ninguém".isValidFirstName())
        assertTrue("joão ninguém".isValidFirstName())
        assertTrue("João".isValidFirstName())
        assertTrue("João1".isNotValidFirstName())
        assertTrue("João Ninguém1".isNotValidFirstName())
        assertTrue("Ana".isValidFirstName())
    }

    @Test
    fun `WHEN has a last name String MUST assert if is valid or invalid`() {
        assertTrue("Sá".isValidLastName())
        assertTrue("Barreto".isValidLastName())
        assertTrue("Sil Va".isValidLastName())
        assertTrue("H0lo".isNotValidLastName())
        assertTrue("Pimenta NaVoz1".isNotValidLastName())
        assertTrue("Pie ro".isValidLastName())
    }

    @Test
    fun `WHEN has a CPF String MUST assert if is valid or invalid`() {
        assertTrue("788.943.200-30".isCpf())
        assertTrue("78894320030".isCpf())
        assertTrue("788.943.200-33".isNotCpf())
        assertTrue("78894320033".isNotCpf())
        assertTrue("788.943.200-3".isNotCpf())
    }

    @Test
    fun `WHEN has password String MUST assert if has min char`() {
        assertTrue("12345678".hasMinCharPassword())
        assertTrue("123456789".hasMinCharPassword())
        assertTrue("1234567".hasNotMinCharPassword())
    }

    @Test
    fun `WHEN has password String MUST assert if has letters`() {
        assertTrue("@A56dv78".hasLetters())
        assertTrue("abcdefgh".hasLetters())
        assertTrue("1234567".hasNotLetters())
    }

    @Test
    fun `WHEN has password String MUST assert if has numbers`() {
        assertTrue("@A56dv78".hasNumbers())
        assertTrue("12345678".hasNumbers())
        assertTrue("#asj!".hasNotNumbers())
    }

    @Test
    fun `WHEN has password String MUST assert if has special char`() {
        assertTrue("@A56dv78".hasSpecialChars())
        assertTrue("!@#$%¨&*(".hasSpecialChars())
        assertTrue("1234asd567".hasNotSpecialChars())
    }

    @Test
    fun `WHEN has registration CEP String assert if os valid or invalid`() {
        assertTrue("".isCepRegistration())
        assertTrue("12345678".isCepRegistration())
        assertTrue("12345-678".isNotCepRegistration())
        assertTrue("123".isNotCepRegistration())
    }

    @Test
    fun `WHEN a VALID number shoes is convert MUST be SUCCESS`() {
        assertTrue("390".fromShoesSize() == "39/40")
        assertTrue("334".fromShoesSize() == "33/34")
    }

/*    @Test
    fun `TEST`() {
        assertTrue("2022-05-18T20:03:22.000Z".extractDate() == "18/05/2022")
        assertTrue("45607123".setMask("## ### ###") == "45 607 123")
        assertTrue("testando isso".capitalize() == "Testando Isso")
        assertTrue(" testando isso".capitalize() == " Testando Isso")
        assertTrue(" TESTanDo ISSo".capitalize() == " Testando Isso")
    }*/
}
