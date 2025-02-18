import kotlin.math.abs

class Squares(val n: Long) {
    // Store as constants, to avoid recomputations
    val sumOfSquares: Long = (0..n).fold(0) { sum, i -> sum + i * i }
    val sum = (0..n).reduce { sum, i -> sum + i }
    val squareOfSum = sum * sum

    fun sumOfSquares(): Long {
        return sumOfSquares
    }

    fun squareOfSum(): Long {
        return squareOfSum
    }

    fun difference(): Long {
        /* If we did not already compute the partial results,
           we could use this more efficient solution;
           see https://stackoverflow.com/a/15593495 
         */
        // val nSq = n * n
        // return abs(((3 * nSq + 2 * n) * (1 - nSq)) / 12)

        return abs(sumOfSquares() - squareOfSum())
    }
}
