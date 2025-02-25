import java.time.*
import kotlin.time.toJavaDuration
import kotlin.time.Duration.Companion.seconds

const val giga = 1000_000_000

class Gigasecond (initialDate: LocalDateTime) {

    // Alternate constructor for dates without times
    constructor (initialDate: LocalDate) : this(initialDate.atTime(0,0))

    private val gigasecond = giga.seconds

    val date: LocalDateTime = initialDate.plus(gigasecond.toJavaDuration())
}
