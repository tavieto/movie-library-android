package dev.tavieto.movielibrary.commons.extension

import dev.tavieto.movielibrary.core.commons.extension.formatDate
import dev.tavieto.movielibrary.core.commons.extension.hasLetters
import dev.tavieto.movielibrary.core.commons.extension.hasMinCharPassword
import dev.tavieto.movielibrary.core.commons.extension.hasNotLetters
import dev.tavieto.movielibrary.core.commons.extension.hasNotMinCharPassword
import dev.tavieto.movielibrary.core.commons.extension.hasNotNumbers
import dev.tavieto.movielibrary.core.commons.extension.hasNotSpecialChars
import dev.tavieto.movielibrary.core.commons.extension.hasNumbers
import dev.tavieto.movielibrary.core.commons.extension.hasSpecialChars
import dev.tavieto.movielibrary.core.commons.extension.isEmail
import dev.tavieto.movielibrary.core.commons.extension.isNotEmail
import dev.tavieto.movielibrary.core.commons.extension.isNotPassword
import dev.tavieto.movielibrary.core.commons.extension.isPassword
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringExtensionTest {

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
    fun `WHEN has a date String MUST assert if is converting the date correctly`() {
        assertTrue("2022-12-01".formatDate() == "01/12/2022")
        assertTrue("2010-01-05".formatDate() == "05/01/2010")
        assertFalse("2010-01-05".formatDate() == "2010-01-05")
    }
}
