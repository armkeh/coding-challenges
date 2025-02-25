object EliudsEggs {

    fun eggCount(number: Int): Int{
        // Int.toString(), and string conversions in most languages,
        // takes an argument specifying the base to use; in this case, 2 for binary.
        return number.toString(2).fold(0) { count, c ->
            when (c) {
                '1' -> count + 1
                else -> count
            }
        }
    }
}
