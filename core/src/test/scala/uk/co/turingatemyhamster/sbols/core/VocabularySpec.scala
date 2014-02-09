package uk.co.turingatemyhamster.sbols.core

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class VocabularySpec extends Specification {

  "Vocabulary" should {

    // base
    "have a non-null value for base_uri" in {
      Vocabulary.base_uri must not be null
    }

    "have a valid base_uri" in {
      Vocabulary.base_uri.toString endsWith "#"
    }

    // documented
    "have a non-null value for documented.name_uri" in {
      Vocabulary.documented.name_uri must not be null
    }

    "have a valid documented.name_uri" in {
      Vocabulary.documented.name_uri.toString must not endWith "#"
    }

    "have a non-null value for documented.displayId_uri" in {
      Vocabulary.documented.displayId_uri must not be null
    }

    "have a valid documented.displayId_uri" in {
      Vocabulary.documented.displayId_uri.toString must not endWith "#"
    }

    "have a non-null value for documented.description_uri" in {
      Vocabulary.documented.description_uri must not be null
    }

    "have a valid documented.description_uri" in {
      Vocabulary.documented.description_uri.toString must not endWith "#"
    }

    // port
    "have a non-null value for port.type_uri" in {
      Vocabulary.port.type_uri must not be null
    }

    "have a valid port.type_uri" in {
      Vocabulary.port.type_uri.toString must not endWith "#"
    }

    "have a non-null value for port.exposes_uri" in {
      Vocabulary.port.exposes_uri must not be null
    }

    "have a valid port.exposes_uri" in {
      Vocabulary.port.exposes_uri.toString must not endWith "#"
    }

    "have a non-null value for port.directionality" in {
      Vocabulary.port.directionality must not be null
    }

    "have a valid documented.description_uri" in {
      Vocabulary.port.directionality.toString must not endWith "#"
    }

    // Directionality
    "have a non-null value for directionality.in_uri" in {
      Vocabulary.directionality.in_uri must not be null
    }

    "have a valid directionality.in_uri" in {
      Vocabulary.directionality.in_uri.toString must not endWith "#"
    }

    "have a non-null value for directionality.out_uri" in {
      Vocabulary.directionality.out_uri must not be null
    }

    "have a valid directionality.out_uri" in {
      Vocabulary.directionality.out_uri.toString must not endWith "#"
    }

    "have a non-null value for directionality.in_out_uri" in {
      Vocabulary.directionality.in_out_uri must not be null
    }

    "have a valid directionality.in_out_uri" in {
      Vocabulary.directionality.in_out_uri.toString must not endWith "#"
    }

    // Instantiation
    "have a non-null value for instantiation.instantiated_uri" in {
      Vocabulary.instantiation.instantiated_uri must not be null
    }

    "have a valid instantiaton.instantiated_uri" in {
      Vocabulary.instantiation.instantiated_uri.toString must not endWith "#"
    }

  }

}