package uk.co.turingatemyhamster.sbols.validation

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import org.specs2.matcher.Matcher

/**
 *
 *
 * @author Matthew Pocock
 */
@RunWith(classOf[JUnitRunner])
class ValidationSpec extends Specification {
  def beSuccess[S, F]: Matcher[Validation[S, F]] =
    ((v: Validation[S, F]) => v match { case Success(_) => true; case _ => false},
      "failed when it should have succeded")

  def beFailure[S, F]: Matcher[Validation[S, F]] =
    ((v: Validation[S, F]) => v match { case Failure(_) => true; case _ => false},
      "succeeded when it should have failed")

  "Validation" should {
    "create a success with .success" in {
      2.success[String] must beSuccess
    }

    "create a failure with .failure" in {
      2.failure[String] must beFailure
    }
  }

}
