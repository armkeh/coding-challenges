import kotlin.math.pow

class BaseConverter(val base: Int, val digits: IntArray) {

    init {
        if (base <= 1) {
            throw IllegalArgumentException("Bases must be at least 2.")
        }

        digits.forEach{ digit ->
            if (digit < 0) {
                throw IllegalArgumentException("Digits may not be negative.")
            } else if (digit >= base) {
                throw IllegalArgumentException("All digits must be strictly less than the base.")
            }
        }

        if (digits.isEmpty()) {
            throw IllegalArgumentException("You must supply at least one digit.")
        }

        if (digits.size > 1 && digits[0] == 0) {
            throw IllegalArgumentException("Digits may not contain leading zeros.")
        }
    }

    // Store the actual value from the [digits]
    val value = digits.reversed().foldIndexed(0) { i, acc, digit ->
        val n = digit * base.toDouble().pow(i).toInt()
        println("Converting $digits: digit $digit at place $i, resulting in value $n.")
        acc + digit * base.toDouble().pow(i).toInt()
    }

    fun convertToBase(newBase: Int): IntArray {
        if (newBase <= 1) {
            throw IllegalArgumentException("Bases must be at least 2.")
        }

        tailrec fun convert(quotient: Int, result: List<Int>): List<Int> {
            if (quotient <= 0) {
                return result
            }

            val digit = quotient % newBase
            val nextQuotient = quotient / newBase

            println("Next digit is $digit; new quotient is $nextQuotient.")

            return convert(nextQuotient, result + digit)
        }

        val converted = convert(value, emptyList()).asReversed().toIntArray()

        if (converted.isEmpty()) {
            return intArrayOf(0)
        }

        return converted
    }
}
