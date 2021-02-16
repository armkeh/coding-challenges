import scala.io.Source

val source = Source.fromFile("../../input/advent-of-code-2020/day1.txt")
val raw_input = source.getLines().toList
source.close()

val input = raw_input.map((s: String) => s.toInt)

/**
  * Find a pair of elements in `xs` which sum to `sum`,
  * if such elements exist.
  * If multiple such pairs exist, returns the pair
  * whose first element has the lowest index and
  * whose second element has the lowest index amongst
  * candidates to pair with that first element.
  */
def summingPair(xs : Seq[Int], sum: Int): Option[Tuple2[Int,Int]] = xs match {
  case Nil => None
  case fst :: rest =>
    rest.collectFirst({case snd if fst + snd == sum => (fst,snd)}) match {
      case Some(pair) => Some(pair)
      case None => summingPair(rest, sum)
    }
}

summingPair(input,2020) match {
  case Some((fst,snd)) => println(s"The product of the summing pair is ${fst * snd}.")
  case None => println("Summing pair not found.")
}

/**
 * Find a sequence of elements of length `n`
 * in `xs` which sum to `sum`,
 * if such elements exist.
 * If multiple such sequences exist, returns the sequence
 * whose first element has the lowest index and
 * whose second element has the lowest index amongst
 * candidates to pair with that first element, etc.
 */
def summingSeq(xs : Seq[Int], sum: Int, n:Int): Either[String,List[Int]] = n match {
  case 0 if sum == 0 => Right(Nil)
  case 0             => Left("Ran out of return space with some leftover amount.")
  case n if n > 0 => xs match {
    case Nil => Left("Ran out of elements with some leftover amount.")
    case fst :: rest if sum >= fst =>
      summingSeq(rest, sum-fst, n-1) match {
        case Right(ys) => Right(fst :: ys)
        case Left(_) => summingSeq(rest,sum,n)
      }
    case _ :: rest => summingSeq(rest,sum,n)
  }
  case n if n < 0 => Left("Cannot sum many negative elements")
}

summingSeq(input,2020,3) match {
  case Right(l) => println(s"The product of the summing triple is ${l.product}.")
  case Left(s) => println(s"Summing triple not found; error ${s}.")
}
