object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {
        if (leftStrand.length != rightStrand.length) {
            // The exception message is enforced by the tests for this exercise; I would prefer:
            // "Hamming Distance is only defined between strands of equal length; ${leftStrand.length} != ${rightStrand.length}!"
            throw IllegalArgumentException("left and right strands must be of equal length")
        }

        return leftStrand.zip(rightStrand).fold(0) { diff, pair ->
            if (pair.first == pair.second) { diff } else { diff + 1 }
        }
    }
}
