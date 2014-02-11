package uk.co.turingatemyhamster.sbols.validation

/**
 *
 *
 * @author Matthew Pocock
 */
trait Validator[S] {
  self =>
  def validate(s: S): Validation[S, Errors]
}

object Validator {

  def apply[S](sv: S => Validation[S, Errors]): Validator[S] = new Validator[S] {
    override def validate(s: S) = sv(s)
  }

  implicit def cast[S, T](v: Validator[S])(implicit ev: T <:< S): Validator[T] = v comap ev

  case class Comap[R, S](v: Validator[S], f: R => S) extends Validator[R] {
    override def validate(r: R) = v.validate(f(r)) map (_ => r)
  }

  case class MapFailure[S](v: Validator[S], e: Errors => Errors) extends Validator[S] {
    override def validate(s: S) = v validate s match {
      case Failure(f) => e(f).failure
      case s => s
    }
  }

  case class SeqValidator[S](v: Validator[S]) extends Validator[Seq[S]] {
    override def validate(ss: Seq[S]) = (ss map v.validate).sequence
  }

//  implicit def cast[S, T](v: Validator[S])(implicit ev: S <:< T): Validator[T] =

  implicit def validatorMonoid[S](implicit sM: Monoid[S]): Monoid[Validator[S]] = new Monoid[Validator[S]] {
    override def zero = alwaysSuccess
    override def mplus(lhs: Validator[S], rhs: Validator[S]) = new Validator[S] {
      override def validate(s: S) = lhs.validate(s) |+| rhs.validate(s)
    }
  }

  def alwaysSuccess[S]: Validator[S] = ((_: S) => true) orFail "Always sucess"
  def alwaysFailure[S]: Validator[S] = ((_: S) => false) orFail "Always failure"

  def notNull[S]: Validator[S] = ((_: S) != null) orFail "value must not be null"
  def is_eq[S](a: S): Validator[S] = (a == (_: S)) orFail f"value must equal `$a`"
  def lt[S](a: S)(implicit oS: Ordering[S]): Validator[S] =
    (oS.lt(_: S, a)) orFail f"value must be less than $a"
  def lteq[S](a: S)(implicit oS: Ordering[S]): Validator[S] =
    (oS.lteq(_: S, a)) orFail f"value must be less than or equal to $a"
  def lteq_pair[S](implicit oS: Ordering[S]): Validator[(S, S)] =
    (oS.lteq _).tupled orFail f"first value must be less than or equal to second value"
  def gt[S](a: S)(implicit oS: Ordering[S]): Validator[S] =
    (oS.gt(_: S, a)) orFail f"value must be greater than $a"
  def gteq[S](a: S)(implicit oS: Ordering[S]): Validator[S] =
    (oS.gteq(_: S, a)) orFail f"value must be greater than or equal to $a"

  def isNone[S]: Validator[Option[S]] = is_eq(None)
  def isSome[S](v: Validator[S]): Validator[Option[S]] = v comap ((_: Option[S]).get)

  def each[S](v: Validator[S]): Validator[Seq[S]] = new Validator[Seq[S]] {
    override def validate(ss: Seq[S]) = (ss map v.validate).sequence
  }
}

//trait ValidatorSyntax {
//  implicit class BooleanValidator[S](val _p: S => Boolean) extends AnyRef {
//    def orFail(dsc: String): Validator[S] = new Validator[S] {
//      override def validate(s: S) = if(_p(s)) s.success else AnError(f"$dsc but fails for `$s`").asErrors.failure
//    }
//  }
//}

sealed trait ErrorNode {
  def asErrors: Errors = Errors(Vector(this))
}

case class AnError(msg: String) extends ErrorNode
case class SubError(msg: String, errors: Errors) extends ErrorNode

case class Errors(errors: Vector[ErrorNode])

object Errors {
  implicit val errorsMonoid: Monoid[Errors] = new Monoid[Errors] {
    override def zero = Errors(Vector())
    override def mplus(lhs: Errors, rhs: Errors) = Errors(lhs.errors |+| rhs.errors)
  }
}

