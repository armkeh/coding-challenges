def spans[A](l: List[A], p: A => Boolean): List[List[A]] = l match {
  case Nil => Nil
  case _ => l.span(p) match {
    case (Nil, Nil) => Nil
    case (Nil, breaker :: rest) => List(breaker) :: spans(rest, p)
    case (first, rest) => first :: spans(rest,p)
  }
}
