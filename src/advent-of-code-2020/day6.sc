import $file.resources, resources._
import scala.io.Source

val source = Source.fromFile("../../input/advent-of-code-2020/day6.txt")
val raw_input = source.getLines().toList
source.close()

val groups = spans(raw_input, ((x: String) => x != ""))
             .filter((x: List[String]) => x != List(""))

def groupAnswers(group: List[String]): List[Char] =
  ('a' to 'z').filter((c: Char) => group.exists(g => g.contains(c))).toList

val answer: Int = groups.foldLeft(0) {
  (accum, g: List[String]) => accum + groupAnswers(g).length
}

println(s"The solution to part 1 is $answer")

def groupConsensus(group: List[String]): List[Char] =
  ('a' to 'z').filter((c: Char) => group.forall(g => g.contains(c))).toList

val answer2: Int = groups.foldLeft(0) {
  (accum, g: List[String]) => accum + groupConsensus(g).length
}

println(s"The solution to part 2 is $answer2")
