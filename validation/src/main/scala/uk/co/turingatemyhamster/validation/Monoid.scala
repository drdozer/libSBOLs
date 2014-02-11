package uk.co.turingatemyhamster.validation

/**
 *
 *
 * @author Matthew Pocock
 */
trait Monoid[M] {
  def zero: M
  def mplus(lhs: M, rhs: M): M
}

object Monoid {
  implicit def listMonoid[T]: Monoid[List[T]] = new Monoid[List[T]] {
    override def zero = List()
    override def mplus(lhs: List[T], rhs: List[T]) = lhs ++ rhs
  }

  implicit def vectorMonoid[T]: Monoid[Vector[T]] = new Monoid[Vector[T]] {
    override def zero = Vector()
    override def mplus(lhs: Vector[T], rhs: Vector[T]) = lhs ++ rhs
  }

  implicit def seqMonoid[T]: Monoid[Seq[T]] = new Monoid[Seq[T]] {
    override def zero = Seq()
    override def mplus(lhs: Seq[T], rhs: Seq[T]) = lhs ++ rhs
  }

}

//trait MonoidSyntax {
//  implicit class MonoidOps[M](val m: M)(implicit mM: Monoid[M]) {
//    def |+| (n: M): M = mM.mplus(m, n)
//  }
//}