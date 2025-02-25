import kotlin.random.Random

class DndCharacter {

    val strength: Int = ability()
    val dexterity: Int = ability()
    val constitution: Int = ability()
    val intelligence: Int = ability()
    val wisdom: Int = ability()
    val charisma: Int = ability()
    val hitpoints: Int = 10 + modifier(constitution)

    companion object {

        fun ability(): Int {
            val rolls = List(4) { _ -> Random.nextInt(1,6) }

            return rolls.sortedDescending().take(3).fold(0) { sum, i -> sum + i }
        }

        fun modifier(score: Int): Int {
            // Integer division rounding works in the wrong direction for negative values in this case.
            return kotlin.math.floor((score - 10).toDouble() / 2).toInt()
        }
    }

}
