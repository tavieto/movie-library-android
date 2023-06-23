package dev.tavieto.movielibrary.core.commons.enums

private const val LATIN_CHAR = "áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ"

enum class RegexEnum(val value: Regex) {
    FULL_NAME("""(^[A-Za-z ($LATIN_CHAR)]{3,} [A-Za-z ($LATIN_CHAR)]{2,}$)""".toRegex()),
    PASSWORD("""^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@${'$'}%^&*-]).{8,}$""".toRegex()),
    PASSWORD_LETTERS("""^(?=.*?[a-zA-Z]).+$""".toRegex()),
    PASSWORD_UPPERCASE("""^(?=.*?[A-Z]).+$""".toRegex()),
    PASSWORD_LOWERCASE("""^(?=.*?[a-z]).+$""".toRegex()),
    PASSWORD_NUMBERS("""^(?=.*?[0-9]).+$""".toRegex()),
    PASSWORD_SPECIAL_CHARACTERS("""^(?=.*?[#?!@${'$'}%^&*-]).+$""".toRegex()),
    EMAIL("""^[a-z0-9_%+-.]+([_%+-.][a-z0-9]+)*@[a-z0-9]+([.-][a-z0-9]+)*\.[a-z]{2,3}$""".toRegex());

    fun match(string: String) = this.value.containsMatchIn(string)
}
