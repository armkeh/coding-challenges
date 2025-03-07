object ResistorColor {

    private val ColorCodes = mapOf(
        "black"  to 0,
        "brown"  to 1,
        "red"    to 2,
        "orange" to 3,
        "yellow" to 4,
        "green"  to 5,
        "blue"   to 6,
        "violet" to 7,
        "grey"   to 8,
        "white"  to 9,
    )

    fun colorCode(input: String): Int {
        val code = ColorCodes.get(input) ?:
            throw IllegalArgumentException("Unknown color $input; use colors method to identify valid colors inputs.")

        return code
    }

    fun colors(): List<String> {
        return ColorCodes.keys.toList()
    }
}
