class NumberSpeller {

    private fun sayDigit(digit: Char): String {
        return when (digit) {
            '0' -> ""
            '1' -> "one"
            '2' -> "two"
            '3' -> "three"
            '4' -> "four"
            '5' -> "five"
            '6' -> "six"
            '7' -> "seven"
            '8' -> "eight"
            '9' -> "nine"
            else -> ""
        }
    }

    private fun sayTeens(digit: Char): String {
        return when (digit) {
            '1' -> "eleven"
            '2' -> "twelve"
            '3' -> "thirteen"
            '4' -> "fourteen"
            '5' -> "fifteen"
            '6' -> "sixteen"
            '7' -> "seventeen"
            '8' -> "eighteen"
            '9' -> "nineteen"
            else -> ""
        }
    }

    private fun sayTensDigit(digit: Char): String {
        return when (digit) {
            '3' -> "thirty"
            '4' -> "forty"
            '5' -> "fifty"
            '6' -> "sixty"
            '7' -> "seventy"
            '8' -> "eighty"
            '9' -> "ninety"
            else -> ""
        }
    }

    private fun sayHundreds(digit: Char): String {
        return when (val hundred = sayDigit(digit)) {
            "" -> ""
            else -> hundred + " hundred"
        }
    }


    private fun sayThreeDigit(hundredsPlace: Char, tensPlace: Char, onesPlace: Char): String {
        val hundreds = sayHundreds(hundredsPlace)
        val tens = when (tensPlace) {
            '1' -> null
            else -> sayTensDigit(tensPlace)
        }
        val ones = when (tensPlace) {
            '1' -> sayTeens(onesPlace)
            else -> sayDigit(onesPlace)
        }

        val hundredsTensSpace = if (hundreds != "" && (tens != "" || ones != "")) { " " } else { "" }
        val tensOnesSpace = if (tens != "" && ones != "") { " " } else { "" }

        return hundreds + hundredsTensSpace + tens + tensOnesSpace + ones
    }

    fun say(n: Long): String {
        // Boundary checks
        if (n <= 0.toLong() || n >= 1_000_000_000_000) {
            // A zero in the 1's place is only spoken if the number is zero.
            if (n == 0.toLong()) {
                return "zero"
            }

            // Out of bounds
            throw IllegalArgumentException("Only non-negative numbers below one trillion are supported; $n is out of range!")
        }

        // Reverse string (so we start from the ones), split into parts, and reverse each part to restore correct ordering.
        val parts = n.toString().reversed().chunked(3).map { s -> s.reversed() }

        val hundreds = parts.getOrElse(0, { _ -> "" })
        val thousands = parts.getOrElse(1, { _ -> "" })
        val millions = parts.getOrElse(2, { _ -> "" })
        val billions = parts.getOrElse(3, { _ -> "" })
        
        return 
    }
}
