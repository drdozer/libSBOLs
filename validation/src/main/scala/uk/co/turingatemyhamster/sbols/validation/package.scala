package uk.co.turingatemyhamster.sbols

/**
 *
 *
 * @author Matthew Pocock
 */
package object validation //extends MonoidSyntax //with ValidationSyntax //with ValidatorSyntax
{
  def validate[S](s: S)(implicit v: Validator[S]): Validation[S, Errors] = v validate s

  implicit class BooleanValidator[S](val _p: S => Boolean) extends AnyVal {
    def orFail(dsc: String): Validator[S] = OrFail(_p, dsc)
  }

  private case class OrFail[S](p: S => Boolean, dsc: String) extends Validator[S] {
    override def validate(s: S): Validation[S, Errors] =
      if(p(s)) s.success else AnError(f"$dsc but fails for `$s`").asErrors.failure
  }

  implicit class ValidationSyntax[S, F](val _v: Validation[S, F]) extends AnyVal {
    def map[T](f: S => T): Validation[T, F] = _v match {
      case Success(s) => f(s).success
      case Failure(f) => f.failure
    }
  }

  implicit class ValidationOps[X](val _x: X) extends AnyRef {
    def success[F]: Validation[X, F] = Success(_x)
    def failure[S]: Validation[S, X] = Failure(_x)
  }

  implicit class SeqValidationOps[S, F](val _sx: Seq[Validation[S, F]]) extends AnyVal {
    def sequence(implicit fM: Monoid[F]): Validation[Seq[S], F] = if(_sx.isEmpty) {
      Seq[S]().success
    } else {
      val ss = _sx map ((_: Validation[S, F]) map (Seq apply _))
      val x = ss reduce (_ |+| _)
      x
    }
  }

  implicit class MonoidOps[M](val m: M)(implicit mM: Monoid[M]) {
    def |+| (n: M): M = mM.mplus(m, n)
  }

  implicit class ValidatorSyntax[S](val _v: Validator[S]) extends AnyVal {
    def comap[R](f: R => S): Validator[R] = Validator.Comap(_v, f)
    def mapFailure(f: Errors => Errors): Validator[S] = Validator.MapFailure(_v, f)
    def forSeq: Validator[Seq[S]] = Validator.SeqValidator(_v)

    def |&&| (w: Validator[S]): Validator[S] = Validator {
      (s: S) => Seq(_v.validate(s), w.validate(s)).sequence map (_.head) }

    def |<>| (w: Validator[S]): Validator[S] = Validator {
      (s: S) => _v validate s match {
        case Failure(f) =>
          w validate s match {
            case Failure(g) =>
              (f |+| g).failure
            case suc =>
              suc
          }
        case suc =>
          suc
      }
    }
  }

//  implicit class SeqValidatorOps[S](val _sx: Seq[Validator[S]]) extends AnyVal {
//    def sequence: Validator[Seq[S]] = new Validator[Seq[S]] {
//      override def validate(s: Seq[S]) = s map validate)
//    }
//  }

  implicit class ValidatorBuilder[S, T](f: S => T) extends AnyRef {
    def as(s: String) = new {
      def validateWith(v: Validator[T]): Validator[S] = v comap f mapFailure (errs => SubError(s, errs).errors)
    }
  }
}
