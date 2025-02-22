object BeerSong {
    private fun remaining(bottles: Int): String {
        return when {
            bottles <= 0 -> "No more bottles"
            bottles == 1 -> "1 bottle"
            else -> "$bottles bottles"
        }
    }

    private fun action(bottles: Int): String {
        val takeDown = "Take %s down and pass it around"

        return when {
            bottles <= 0 -> "Go to the store and buy some more"
            bottles == 1 -> String.format(takeDown, "it")
            else -> String.format(takeDown, "one")
        }
    }

    private tailrec fun buildVerses(startBottles: Int, stopAfter: Int, verses: String): String {
        if (startBottles < stopAfter || startBottles < 0) {
            return verses
        }

        val before = remaining(startBottles)
        val action = action(startBottles)
        val after = remaining(if (startBottles > 0) { startBottles-1 } else { 99 }) // Refill if we have none.
        val verse =
            "${before} of beer on the wall, " +
            "${before.lowercase()} of beer.\n" +
            "${action}, " +
            "${after.lowercase()} of beer on the wall."

        return buildVerses(startBottles-1, stopAfter, verses + "\n\n" + verse)
    }

    fun verses(startBottles: Int, stopAfter: Int): String {
        return buildVerses(startBottles, stopAfter, "").trim() + "\n"
    }
}
