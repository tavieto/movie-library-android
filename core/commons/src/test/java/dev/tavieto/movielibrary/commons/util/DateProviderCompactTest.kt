package dev.tavieto.movielibrary.commons.util

import dev.tavieto.movielibrary.core.commons.util.DateProviderCompact
import dev.tavieto.movielibrary.core.commons.util.toPattern
import org.junit.Test
import java.util.Date

internal class DateProviderCompactTest {
    @Test
    fun testIsToday() {
        assert(DateProviderCompact.isToday("2001-07-04T12:08:56").not())
        assert(DateProviderCompact.isToday(Date().toPattern()))
    }
}