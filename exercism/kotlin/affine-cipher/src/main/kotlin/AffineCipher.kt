object AffineCipher {

    private fun coprimeTo26(a: Int): Boolean {
        // Since 26 is a constant here, the check is relatively simple;
        // 26 has only two prime factors.
        return a % 2 != 0 && a % 13 != 0
    }

    fun encode(input: String, a: Int, b: Int): String {
        if (!coprimeTo26(a)) {
            throw IllegalArgumentException("Value a must be coprime to 26; $a is not!")
        }

        // Check for invalid characters, remove spaces and punctuation,
        // and lowercase all letters for simplicity.
        val filteredLowercase = input.lowercase().fold("") { acc, c ->
            when {
                c.isLetterOrDigit() -> acc + c.lowercase()
                c.isWhitespace() -> acc
                c in setOf('.', '!', '?', ',', ';', ':') -> acc
                else -> throw IllegalArgumentException("Illegal character '$c' encountered during encoding of string \"$input\"!")
            }
        }

        val ciphered = filteredLowercase.map(fun(c: Char): Char {
            if (c.isDigit()) {
                return c // Digits are not to be encoded
            } else if (!c.isLetter()) {
                throw IllegalArgumentException("Input string must consist only of letters, spaces, digits or punctuation; non-letter '$c' found in input \"$input\"!")
            }

            val i = c.code - 'a'.code
            val ex = (a * i + b) % 26

            return ('a'.code + ex).toChar()
        }).joinToString(separator="")

        // Split ciphered text into groups of 5 characters, split by spaces.
        return ciphered.windowed(5, step=5, partialWindows=true).joinToString(separator=" ")
    }

    fun decode(input: String, a: Int, b: Int): String {
        if (!coprimeTo26(a)) {
            throw IllegalArgumentException("Value a must be coprime to 26; $a is not!")
        }

        // Using the extended Euclidean algorithm would be better in general;
        // since the alphabet size is fixed here, we can cheat with this simple iteration.
        val aInv = (1..26).firstOrNull { i -> (a * i) % 26 == 1 } ?: 1

        // If we wanted to enforce the input contain a space between every 5 characters, this would do it:
        // if (input.drop(5).windowed(1, step=5).any { c -> c != " " }) {
        //     throw IllegalArgumentException("Encoded string must contain a space every 5 characters!")
        // }

        // Instead of that check, though, we just strip all spaces.
        // Also force all letters to lowercase for simplicity.
        return input.lowercase().filter { c -> !c.isWhitespace() }.map (fun(c: Char): Char {
            if (c.isDigit()) {
                return c // Digits are not encoded
            } else if (!c.isLetter()) {
                throw IllegalArgumentException("Input string must consist only of letters, spaces or digits; non-letter '$c' found in input \"$input\"!")
            }

            val ex = c.code - 'a'.code

            // There's a subtle trap here; if ex < b, a^-1 * (ex - b) may be (very) negative.
            // Use floorMod rather than %, since it will always return a value between 0 and 25.
            val dy = Math.floorMod(aInv * (ex - b), 26)

            return ('a'.code + dy).toChar()
        }).joinToString(separator="")
    }
}
