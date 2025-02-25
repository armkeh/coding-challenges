// Abstract dart game class for different dart board types.
class DartsGame(
    val innerRadius: Int = 1,
    val middleRadius: Int = 5,
    val outerRadius: Int = 10,
    val innerPoints: Int = 10,
    val middlePoints: Int = 5,
    val outerPoints: Int = 1,
    val missPoints: Int = 0,
) {

    init {
        if (innerRadius > middleRadius || middleRadius > outerRadius) {
            throw IllegalArgumentException(
                "Dart board inner section radius must not be greater than middle section radius, "
                    + "and middle section radius must not be greater than outer section radius, "
                    + "but $innerRadius > $middleRadius and/or $middleRadius > $outerRadius!"
            )
        }
    }

    fun score(x: Number, y: Number): Int {
        val dx = x.toDouble()
        val dy = y.toDouble()
        val distFromOrigin = kotlin.math.sqrt(dx * dx + dy * dy)

        when {
            distFromOrigin <= innerRadius  -> return innerPoints
            distFromOrigin <= middleRadius -> return middlePoints
            distFromOrigin <= outerRadius  -> return outerPoints
            else -> return missPoints
        }
    }
}

// Concrete dart game object with given radiuses and score for the exercism problem.
val Darts = DartsGame()
