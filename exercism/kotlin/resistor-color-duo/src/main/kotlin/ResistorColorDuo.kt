object ResistorColorDuo {

    val ColorCodes = mapOf(
        Color.BLACK  to 0,
        Color.BROWN  to 1,
        Color.RED    to 2,
        Color.ORANGE to 3,
        Color.YELLOW to 4,
        Color.GREEN  to 5,
        Color.BLUE   to 6,
        Color.VIOLET to 7,
        Color.GREY   to 8,
        Color.WHITE  to 9,
    )

    fun value(vararg colors: Color): Int {
        val first  = colors.getOrNull(0)?.let { ColorCodes.get(it).toString() } ?: ""
        val second = colors.getOrNull(1)?.let { ColorCodes.get(it).toString() } ?: ""

        return (first + second).toInt()
    }
}
