package uk.co.turingatemyhamster.sbols.context

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification

@RunWith(classOf[JUnitRunner])
class VocabularySpec extends Specification {
  "Vocabulary" should {
    "have a non-null value for contextPrefix_value" in {
      Vocabulary.contextPrefix_value must not be null
    }

    "have a non-null value for contextNamespace_uri" in {
      Vocabulary.contextNamespace_uri must not be null
    }

    // context
    "have a non-null value for context.type_uri" in {
      Vocabulary.context.type_uri must not be null
    }

    "have a valid context.type_uri" in {
      Vocabulary.context.type_uri.toString must not endWith "#"
    }
  }
}