package uk.co.turingatemyhamster.sbols.validation

/**
 *
 *
 * @author Matthew Pocock
 */
sealed trait Validation[S, F]

case class Success[S, F](value: S) extends Validation[S, F]
case class Failure[S, F](value: F) extends Validation[S, F]

object Validation {
  implicit def validationMonoid[S, F](implicit mS: Monoid[S], mF: Monoid[F])
  : Monoid[Validation[S, F]] = new Monoid[Validation[S, F]] {
    override def zero = mS.zero.success
    override def mplus(lhs: Validation[S, F], rhs: Validation[S, F]) = (lhs, rhs) match {
      case (Success(a), Success(b)) => (a |+| b).success
      case (Failure(a), Failure(b)) => (a |+| b).failure
      case (f@Failure(_), Success(_)) => f
      case (Success(_), f@Failure(_)) => f
    }
  }


  implicit def cast[S, T, F](v: Validation[S, F])(implicit ev: S <:< T): Validation[T, F] = v map ev

}
