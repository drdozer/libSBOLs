package uk.co.turingatemyhamster.sbols.core

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class VocabularySpec extends Specification {

  "Vocabulary" should {
    "have a non-null value for base_uri" in {
      Vocabulary.base_uri must not be null
    }

    "have a non-null value for documented.name_uri" in {
      Vocabulary.documented.name_uri must not be null
    }

    "have a non-null value for documented.displayId_uri" in {
      Vocabulary.documented.displayId_uri must not be null
    }

    "have a non-null value for documented.description_uri" in {
      Vocabulary.documented.description_uri must not be null
    }
  }

}