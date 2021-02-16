import scala.io.Source

val source = Source.fromFile("../../input/advent-of-code-2020/day3.txt")
val input = source.getLines.toList
source.close()

def encounters(map: List[String],
               entity: Char => Boolean,
               x_position: Int,
               velocity: Tuple2[Int,Int]): Int = {
  val (x_velocity,y_velocity) = velocity
  map.length match {
    case n if n <= y_velocity => 0
    case n =>
      val next_x = (x_position + x_velocity)
      val remaining_map = map.drop(y_velocity)
      val map_width = remaining_map(0).length
      val encounter = if (entity(remaining_map(0)(next_x % map_width))) 1 else 0
      encounters(remaining_map, entity, next_x, velocity) + encounter
  }
}

val answer = encounters(input, {_ == '#'}, 0, (3,1))

println(s"On this hill, with this velocity, we will encounter $answer trees.")

// The @tailrec annotation on a method results in a static error
// if the definition is not actually tail recursive.
import scala.annotation.tailrec

def encountersTR(map: List[String],
               entity: Char => Boolean,
               x_position: Int,
               velocity: Tuple2[Int,Int]): Int = {
  @tailrec
  def encounters_helper(map: List[String],
                        x_position: Int,
                        count: Int): Int = {
    val (x_velocity,y_velocity) = velocity
    map.length match {
      case n if n <= y_velocity => count
      case n =>
        val next_x = (x_position + x_velocity)
        val remaining_map = map.drop(y_velocity)
        val map_width = remaining_map(0).length
        val encounter = if (entity(remaining_map(0)(next_x % map_width))) 1 else 0
        encounters_helper(remaining_map, next_x, count+encounter)
    }
  }
  encounters_helper(map,x_position,0)
}

val e11: Long = encountersTR(input, {_ == '#'}, 0, (1,1))
val e31: Long = encountersTR(input, {_ == '#'}, 0, (3,1))
val e51: Long = encountersTR(input, {_ == '#'}, 0, (5,1))
val e71: Long = encountersTR(input, {_ == '#'}, 0, (7,1))
val e12: Long = encountersTR(input, {_ == '#'}, 0, (1,2))

val answer2: Long = e11 * e31 * e51 * e71 * e12

println(s"On this hill, with these velocities, the product of the number of trees we will encounter is $answer2")
