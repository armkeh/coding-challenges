import scala.io.Source

val source = Source.fromFile("../../input/advent-of-code-2020/day5.txt")
val input = source.getLines().toList
source.close()

sealed trait BinSpaceModifier
case object IncreaseLower extends BinSpaceModifier
case object DecreaseUpper extends BinSpaceModifier

def shrinkBinSpace(space: Tuple2[Int,Int],
                   c: List[BinSpaceModifier]):
    Tuple2[Int,Int] = c match {
  case Nil => space
  case IncreaseLower :: rest => {
    val lower = space._1
    val upper = space._2
    val increased = ((upper + lower).toFloat / 2).ceil.toInt
    shrinkBinSpace(Tuple2(increased,upper), rest)
  }
  case DecreaseUpper :: rest => {
    val lower = space._1
    val upper = space._2
    val decreased = ((upper + lower).toFloat / 2).floor.toInt
    shrinkBinSpace(Tuple2(lower,decreased), rest)
  }
}

def parseSeatCode(code: String):
    Tuple2[List[BinSpaceModifier],List[BinSpaceModifier]] = {
  val (rowLetters, columnLetters) = code.splitAt(7)
  val rowCommands = rowLetters.map(l => l match {
    case 'F' => DecreaseUpper
    case 'B' => IncreaseLower}).toList
  val columnCommands = columnLetters.map(l => l match {
    case 'L' => DecreaseUpper
    case 'R' => IncreaseLower}).toList

  Tuple2(rowCommands,columnCommands)
}

val highest_id = input.map(code => {
  val (rowCommands, columnCommands) = parseSeatCode(code)
  val row = shrinkBinSpace(Tuple2(0,127),rowCommands)._1
  val column = shrinkBinSpace(Tuple2(0,7),columnCommands)._1

  row * 8 + column
}).max

println(s"The highest ID for a seat is $highest_id.")

val seat_ids = input.map(code => {
  val (rowCommands, columnCommands) = parseSeatCode(code)
  val row = shrinkBinSpace(Tuple2(0,127),rowCommands)._1
  val column = shrinkBinSpace(Tuple2(0,7),columnCommands)._1

  row * 8 + column
})

val our_id = (0 to 1023).find(id =>
  seat_ids.contains(id-1) && !seat_ids.contains(id) && seat_ids.contains(id+1))

our_id match {
  case Some(id) => println(s"Our seat is $id.")
  case None => println("Oops; didn't find our seat id!")
}
