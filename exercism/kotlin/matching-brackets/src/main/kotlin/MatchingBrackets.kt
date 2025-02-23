object MatchingBrackets {

    fun pair(l: Char, r: Char): Boolean {
        return when (l) {
            '[' -> r == ']'
            '{' -> r == '}'
            '(' -> r == ')'
            else -> false
        }
    }

    fun isValid(input: String): Boolean {

        tailrec fun match(remaining: List<Char>, rights: List<Char>): Boolean {
            val first = remaining.firstOrNull()
            val rest = remaining.drop(1)

            return when (first) {
                null ->
                    // End of list reached, check all opening brackets were matched.
                    rights.isEmpty()

                '[', '{', '(' ->
                    // Add bracket to stack and continue.
                    match(rest, listOf(first) + rights)

                ')', '}', ']' ->
                    if (rights.firstOrNull().let { it != null && pair(it, first) }) {
                        // Match found, drop bracket from stack and continue.
                        match(rest, rights.drop(1))
                    } else {
                        // Mismatch found!
                        false
                    }

                else ->
                    // Ignore non-bracket characters, continue.
                    match(rest, rights)
            }
        }

        return match(input.toList(), emptyList())
    }
}
