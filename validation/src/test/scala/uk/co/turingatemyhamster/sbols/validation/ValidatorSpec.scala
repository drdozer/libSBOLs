package uk.co.turingatemyhamster.sbols.validation

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import org.specs2.matcher.Matcher
import Validator._

/**
 *
 *
 * @author Matthew Pocock
 */
@RunWith(classOf[JUnitRunner])
class ValidatorSpec extends Specification {
  def beSuccess[S, F]: Matcher[Validation[S, F]] =
    ((v: Validation[S, F]) => v match { case Success(_) => true; case _ => false},
      "failed when it should have succeded")

  def beFailure[S, F]: Matcher[Validation[S, F]] =
    ((v: Validation[S, F]) => v match { case Failure(_) => true; case _ => false},
      "succeeded when it should have failed")

  "Validator" should {
    "map failure correctly" in {
      val newErrors = Errors(Vector(AnError("Hi mum")))
      val nnmf = notNull[String] mapFailure { _ => newErrors }

      nnmf.validate(null) must_== Failure(newErrors)
    }

    "map SubError into a failure" in {
      val nnmf = notNull[String] mapFailure { errs => Errors(Vector(SubError("message", errs))) }

      nnmf.validate(null) must_== Failure(
        Errors(Vector(
          SubError("message", Errors(Vector(
            AnError("value must not be null but fails for `null`")))))))
    }

    "wrap errors with `_ as _ validateWith _` syntax" in {
      val sV = ValidatorBuilder((_: Some[String]).get) as "get" validateWith notNull

      val failure = sV.validate(Some(null))

      failure must_== Failure(
        Errors(Vector(
          SubError("get", Errors(Vector(
            AnError("value must not be null but fails for `null`")))))))
    }
  }

}
