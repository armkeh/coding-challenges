package io.github.armkeh

object Day03 {
    fun part1(input: String) {
        val lineLength = input.indexOf('\n')
        val padLine = String(CharArray(lineLength) { '.' })
        val input = padLine + '\n' + input + '\n' + padLine // Add padding lines, for ease of windowing.

        val sum =  input.lines().map { line -> "$line." } // Add a '.' as padding on the end of each line, to avoid special logic for end-of-line.
            .windowed(size = 3, step = 1, partialWindows = false).map { Triple(it[0], it[1], it[2]) }
            .sumOf { (above, row, below) ->
                row.foldIndexed(Triple("", false, 0))
                { i, (runningDigits: String, foundAdjacentSymbol: Boolean, rowSum: Int), _ ->
                    val adjacentSymbolHere = above[i].isSymbol() || row[i].isSymbol() || below[i].isSymbol()
                    val foundAdjacentSymbol = foundAdjacentSymbol || adjacentSymbolHere

                    when {
                        row[i].isDigit() -> Triple(runningDigits + row[i], foundAdjacentSymbol, rowSum) // Accumulate digits, track symbols.
                        !row[i].isDigit() && foundAdjacentSymbol -> Triple(
                            "",
                            adjacentSymbolHere, // Might be the beginning of a part number.
                            rowSum + if (runningDigits.isNotEmpty()) runningDigits.toInt() else 0 // Might be the end of a part number.
                        )
                        else -> Triple("", adjacentSymbolHere, rowSum)
                    }
                }.let { (_, _, sum) -> sum }
            }

        println("The sum of the part numbers is $sum")
    }

    private fun Char.isSymbol(): Boolean = this.let { c -> c != '.' && !this.isDigit() }
}