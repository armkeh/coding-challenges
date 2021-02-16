import scala.io.Source

val source = Source.fromFile("../../input/advent-of-code-2020/day2.txt")
val input = source.getLines.toList
source.close()

def parsePolicy(s: String): Tuple4[Int,Int,Char,String] = {
  val pattern = "([0-9]+)-([0-9]+) ([A-Za-z]): ([A-Za-z]+)".r
  val pattern(low, high, c, pass) = s
  return (low.toInt,high.toInt,c.head,pass)
}

def satisfiesPolicy(s : String): Boolean = {
  val (low,high,c,pass) = parsePolicy(s)
  val occurrences = pass.count(_ == c)
  return low <= occurrences && occurrences <= high
}

val answer = input.foldLeft(0){
  (accum, i) => if (satisfiesPolicy(i)) accum + 1 else accum
}

println(s"$answer passwords are valid according to the part 1 policy.")

val answer2 = input.foldLeft(0){
  (accum, i) => {
    val (m,n,c,pass) = parsePolicy(i)
    val m_is_c = pass(m-1) == c
    val n_is_c = pass(n-1) == c
    // XOR is "not equal"
    if (m_is_c != n_is_c)
      accum + 1
    else
      accum
  }
}

println(s"$answer2 passwords are valid according to the part 2 policy.")
