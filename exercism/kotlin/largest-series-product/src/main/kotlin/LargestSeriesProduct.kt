class Series(val input: String) {

    init {
        if (input.any { c -> !c.isDigit() }) {
            throw IllegalArgumentException("Input must consist of only digits, but was \"$input\"!")
        }
    }

    fun getLargestProduct(span: Int): Long {
        if (span < 1 || span > input.length) {
            throw IllegalArgumentException("Span must be a positive integer not more than the length of the input (${input.length}), but was $span!")
        }

        return input.windowed(span).fold(0) { max, window ->
            val windowProduct: Long = window.fold(1) { prod, c -> prod * c.digitToInt() }
            kotlin.math.max(max, windowProduct)
        }
    }
}
