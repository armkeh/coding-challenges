object Bob {

    fun hey(input: String): String {
        val isQuestion = input.trimEnd().lastOrNull() == '?'
        // Note: It only counts as yelling if there is at least one letter, and all letters are uppercase.
        val isYelled = input.any { c -> c.isLetter() } &&
            input.all { c -> if (c.isLetter()) { c.isUpperCase() } else true }
        val isSilence = input.all { c -> c.isWhitespace() }

        return when {
            isQuestion && isYelled -> "Calm down, I know what I'm doing!"
            isQuestion -> "Sure."
            isYelled -> "Whoa, chill out!"
            isSilence -> "Fine. Be that way!"
            else -> "Whatever."
        }
    }
}
