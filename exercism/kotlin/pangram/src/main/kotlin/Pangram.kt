object Pangram {

    fun isPangram(input: String): Boolean {
        val uniqueLetters = input.lowercase()
            .filter { c -> c.isLetter() }
            .associateWith {
                // Create a map with the set of occurring characters as keys.
                // Values are unimportant; this empty body associates all to unit.
            }
            .keys // Pull the set out from the keys.

        return uniqueLetters.size == 26
    }
}
