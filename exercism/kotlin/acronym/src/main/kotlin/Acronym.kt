object Acronym {
    fun generate(phrase: String) : String {
        return phrase
            .filter { e -> e.isLetterOrDigit() || e == ' ' || e == '-' } // Ignore special characters other than spaces or dashes
            .split(" ", "-") // Space and dash are word separators
            .filter { e -> e != "" && e != "-" } // Remove empty strings and lone dashes
            .fold("") { acc, next -> "$acc${next.first().uppercase()}" } // Finally, join together first letters/digits
    }
}
