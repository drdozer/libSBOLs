package uk.co.turingatemyhamster.sbols.model

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class VocabularySpec extends Specification {

  "Vocabulary " should {
    "have a non-null value for modelPrefix_value" in {
      Vocabulary.modelPrefix_value must not be null
    }

    "have a non-null value for modelNamespace_uri" in {
      Vocabulary.modelNamespace_uri must not be null
    }

    "have a non-null value for model.type_uri" in {
      Vocabulary.model.type_uri must not be null
    }

    "have a valid model.type_uri" in {
      Vocabulary.model.type_uri.toString must not endWith "#"
    }

    "have a non-null value for model.source_uri" in {
      Vocabulary.model.source_uri must not be null
    }

    "have a valid model.source_uri" in {
      Vocabulary.model.source_uri.toString must not endWith "#"
    }

    "have a non-null value for model.language_uri" in {
      Vocabulary.model.language_uri must not be null
    }

    "have a valid model.language_uri" in {
      Vocabulary.model.language_uri.toString must not endWith "#"
    }

    "have a non-null value for model.framework_uri" in {
      Vocabulary.model.framework_uri must not be null
    }

    "have a valid model.framework_uri" in {
      Vocabulary.model.framework_uri.toString must not endWith "#"
    }

    "have a non-null value for model.role_uri" in {
      Vocabulary.model.role_uri must not be null
    }

    "have a valid model.role_uri" in {
      Vocabulary.model.role_uri.toString must not endWith "#"
    }
  }

}