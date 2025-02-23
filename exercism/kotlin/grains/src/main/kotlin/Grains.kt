import java.math.BigInteger

object Board {

    fun getGrainCountForSquare(square: Int): BigInteger {
        when {
            square <= 0 -> throw IllegalArgumentException("Cannot get ")
            square > 64 -> throw IllegalArgumentException("Cannot get ")
            square == 1 -> return 1.toBigInteger()
            else -> return 2.toBigInteger() * getGrainCountForSquare(square - 1)
        }
    }

    fun getTotalGrainCount(): BigInteger {
        return (1..64).fold(0.toBigInteger()) { count, sq -> count + getGrainCountForSquare(sq) }
    }
}
