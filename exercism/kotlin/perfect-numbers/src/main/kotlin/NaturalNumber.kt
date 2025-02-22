enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun aliquotSum(n: Int): Int {
    if (n <= 0) {
        throw IllegalArgumentException("Nicomachus classification is only defined for positive integers; cannot classify $n!")
    } else if (n == 1 || n == 2) {
        // For small arguments, rounding at the end of the range causes misbehaviour;
        // handle them specially.
        return n - 1
    }

    return 1 + (2..(n/2)+1).fold(0) { sum, i -> if (n % i == 0) { sum + i } else { sum } }
}

fun classify(n: Int): Classification {
    val aliquotSum = aliquotSum(n)

    return when {
        n > aliquotSum -> Classification.DEFICIENT
        n < aliquotSum -> Classification.ABUNDANT
        else -> Classification.PERFECT
    }
}
