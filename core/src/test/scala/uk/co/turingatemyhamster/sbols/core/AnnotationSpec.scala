package uk.co.turingatemyhamster.sbols.core

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification

import uk.co.turingatemyhamster.sbols.validation._
import org.specs2.matcher.Matcher

/**
 *
 *
 * @author Matthew Pocock
 */
@RunWith(classOf[JUnitRunner])
class AnnotationSpec extends Specification {
  def beSuccess[S, F]: Matcher[Validation[S, F]] =
    ((v: Validation[S, F]) => v match { case Success(_) => true; case _ => false},
      "failed when it should have succeded")

  def beFailure[S, F]: Matcher[Validation[S, F]] =
    ((v: Validation[S, F]) => v match { case Failure(_) => true; case _ => false},
      "succeeded when it should have failed")

  "StringValue validation" should {
    "Succeed with a non-null string value" in {
      validate(StringValue("Hi mum")) must beSuccess
    }

    "Fail with a null string value" in {
      validate(StringValue(null)) must beFailure
    }
  }

}
