class DiamondPrinter {
    
    fun printToList(c: Char): List<String> {
        /**
         * Builds a list of the bottom half of a diamond starting at letter [c],
         * including the middle line of the diamond.
         * This helper function never checks that [c] is a valid letter code;
         * instead it uses the remaining [space] to determine when building is done.
         *
         * @param pad   Length of the outside padding portion at the next line. Should initially be 0.
         * @param space Length of the space between the letters at the next line.
         * @param c     Character to print at the next line. Decreases towards "a".
         * @param halfDiamond Accumulator value for the diamond. Should initially be empty.
         */
        tailrec fun buildHalfDiamond(pad: Int, space: Int, c: Char, halfDiamond: List<String>): List<String> {
            val padStr = " ".repeat(pad)
            if (space <= 0) {
                return halfDiamond + (padStr + c + padStr)
            }

            val spaceStr = " ".repeat(space)
            val line = padStr + c + spaceStr + c + padStr
            return buildHalfDiamond(pad+1, space-2, (c.code - 1).toChar(), halfDiamond + line)
        }

        if (! c.isLetter()) {
            throw IllegalArgumentException("DiamondPrinter input must be a letter")
        }

        val a = if (c.isUpperCase()) { 'A' } else { 'a' }
        val space = 2 * (c.code - a.code) - 1
        
        val halfDiamond = buildHalfDiamond(0, space, c, emptyList())

        return halfDiamond.asReversed() + halfDiamond.drop(1)
    }
}
