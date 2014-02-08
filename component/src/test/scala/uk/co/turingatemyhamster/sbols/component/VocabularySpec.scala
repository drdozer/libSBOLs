package uk.co.turingatemyhamster.sbols.component

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class VocabularySpec extends Specification {

  "Vocabulary" should {
    "have a non-null value for component.componentType_uri" in {
      Vocabulary.component.componentType_uri must not be null
    }

    "have a non-null value for sequenceComponent.functionType_uri" in {
      Vocabulary.sequenceComponent.functionType_uri must not be null
    }

    "have a non-null value for sequenceComponent.sequence_uri" in {
      Vocabulary.sequenceComponent.sequence_uri must not be null
    }

    "have a non-null value for sequenceComponent.sequenceAnnotation_uri" in {
      Vocabulary.sequenceComponent.sequenceAnnotation_uri must not be null
    }

    "have a non-null value for sequence.primarySequence_uri" in {
      Vocabulary.sequence.primarySequence_uri must not be null
    }

    "have a non-null value for sequenceAnnotation.bioStart_uri" in {
      Vocabulary.sequenceAnnotation.bioStart_uri must not be null
    }

    "have a non-null value for sequenceAnnotation.bioEnd_uri" in {
      Vocabulary.sequenceAnnotation.bioEnd_uri must not be null
    }

    "have a non-null value for sequenceAnnotation.type_uri" in {
      Vocabulary.sequenceAnnotation.type_uri must not be null
    }

    "have a non-null value for sequenceAnnotation.subComponent_uri" in {
      Vocabulary.sequenceAnnotation.subComponent_uri must not be null
    }

    "have a non-null value for orientedAnnotation.type_uri" in {
      Vocabulary.orientedAnnotation.type_uri must not be null
    }

    "have a non-null value for orientedAnnotation_uri" in {
      Vocabulary.orientedAnnotation.orientation_uri must not be null
    }

    "have a non-null value for orientation.inline_uri" in {
      Vocabulary.orientation.inline_uri must not be null
    }

    "have a non-null value for orientation.reverseComplement_uri" in {
      Vocabulary.orientation.reverseComplement_uri must not be null
    }

    "have a non-null value for dnaComponent.type_uri" in {
      Vocabulary.dnaComponent.type_uri must not be null
    }

    "have a non-null value for dnaComponent.componentTypeConstant" in {
      Vocabulary.dnaComponent.componentType_value_uri must not be null
    }

    "have a non-null value for dnaSequence.type_uri" in {
      Vocabulary.dnaSequence.type_uri must not be null
    }

    "have a non-null value for rnaComponent.type_uri" in {
      Vocabulary.rnaComponent.type_uri must not be null
    }

    "have a non-null value for rnaComponent.componentTypeConstant" in {
      Vocabulary.rnaComponent.componentType_value_uri must not be null
    }

    "have a non-null value for rnaSequence.type_uri" in {
      Vocabulary.rnaSequence.type_uri must not be null
    }

    "have a non-null value for proteinComponent.type_uri" in {
      Vocabulary.proteinComponent.type_uri must not be null
    }

    "have a non-null value for proteinComponent.componentTypeConstant" in {
      Vocabulary.proteinComponent.componentType_value_uri must not be null
    }

    "have a non-null value for proteinSequence.type_uri" in {
      Vocabulary.proteinSequence.type_uri must not be null
    }
  }
}
