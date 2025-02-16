import kotlin.math.sqrt
import kotlin.math.ceil

object CryptoSquare {

    fun ciphertext(plaintext: String): String {
        // Normalise plaintext
        val normalised = plaintext.lowercase().filter { e -> e.isLetterOrDigit() }

        // To be roughly square shaped, we use sqrt of the normalised string's length columns
        val columns = ceil(sqrt(normalised.length.toDouble())).toInt()

        // And may use one less row, or the same number as of columns
        val rows = if (columns * (columns-1) > normalised.length) columns-1 else columns

        val squared: List<String> = (0..columns-1).fold(emptyList()) { square, i ->
            // Construct line by stepping through [padded] in steps of size [columns], starting at offset [i]
            val next = (0..rows-1).fold("") { line, j ->
                // Append next character to the line, appending space if out of bounds
                line + normalised.getOrElse(j * columns + i) {' '}
            }

            // Append line to the square
            square + next
        }

        return squared.joinToString(" ")
    }

}
