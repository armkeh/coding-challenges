import kotlin.math.pow

object ArmstrongNumber {

    fun check(input: Int): Boolean {
        val inputString = input.toString()
        val armstrong = inputString.fold(0) { acc, next -> 
                // Since the elements are char, and char to number conversion gives character codes,
                // first convert to string, then to number.
                acc + next.toString().toDouble().pow(inputString.length).toInt()
            }

        return input == armstrong
    }

}
