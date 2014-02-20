package uk.co.turingatemyhamster.sbols.component

import java.util.Properties
import uk.co.turingatemyhamster.sbols.core.{VocabularyBase, URI}

/**
 *
 *
 * @author Matthew Pocock
 */
object Vocabulary extends Vocabulary({
  val props = new Properties()
  props.load(classOf[Vocabulary].getResourceAsStream("Vocabulary.properties"))
  props
})

class Vocabulary(props: Properties) extends VocabularyBase(props) {
  val componentPrefix = "componentPrefix"
  val componentPrefix_value = lookupRaw(componentPrefix)

  val componentNamespace = "componentNamespace"
  val componentNamespace_uri = lookup(componentNamespace)

  object component {
    val componentType = "Component.componentType"
    val componentType_uri = lookup(componentType)
  }

  object genericComponent {
    val `type` = "GenericComponent.type"
    val type_uri = lookup(`type`)
  }

  object sequenceComponent {
    val functionType = "SequenceComponent.functionType"
    val functionType_uri = lookup(functionType)

    val sequence = "SequenceComponent.sequence"
    val sequence_uri = lookup(sequence)

    val sequenceAnnotation = "SequenceComponent.sequenceAnnotation"
    val sequenceAnnotation_uri = lookup(sequenceAnnotation)
  }

  object sequence {
    val primarySequence = "Sequence.primarySequence"
    val primarySequence_uri = lookup(primarySequence)
  }

  object sequenceAnnotation {
    val `type` = "SequenceAnnotation.type"
    val type_uri = lookup(`type`)

    val bioStart = "SequenceAnnotation.bioStart"
    val bioStart_uri = lookup(bioStart)

    val bioEnd = "SequenceAnnotation.bioEnd"
    val bioEnd_uri = lookup(bioEnd)

    val subComponent = "SequenceAnnotation.subComponent"
    val subComponent_uri = lookup(subComponent)
  }

  object orientedAnnotation {
    val `type` = "OrientedAnnotation.type"
    val type_uri = lookup(`type`)

    val orientation = "OrientedAnnotation.orientation"
    val orientation_uri = lookup(orientation)
  }

  object orientation {
    val inline = "Orientation.inline"
    val inline_uri = lookup(inline)

    val reverseComplement = "Orientation.reverseComplement"
    val reverseComplement_uri = lookup(reverseComplement)
  }

  object proteinComponent {
    val `type` = "ProteinComponent.type"
    val type_uri = lookup(`type`)

    val componentType_value = "ProteinComponent.componentType_value"
    val componentType_value_uri = lookup(componentType_value)
  }

  object proteinSequence {
    val `type` = "ProteinSequence.type"
    val type_uri = lookup(`type`)
  }

  object genericSequenceComponent {
    val `type` = "GenericComponent.type"
    val type_uri = lookup(`type`)
  }

  object genericSequence {
    val `type` = "GenericSequence.type"
    val type_uri = lookup(`type`)

    val legalCharacters = "GenericSequence.legalCharacters"
    val legalCharacters_uri = lookup(legalCharacters)
  }

  object dnaComponent {
    val `type` = "DnaComponent.type"
    val type_uri = lookup(`type`)

    val componentType_value = "DnaComponent.componentType_value"
    val componentType_value_uri = lookup(componentType_value)
  }

  object dnaSequence {
    val `type` = "DnaSequence.type"
    val type_uri = lookup(`type`)
  }

  object rnaComponent {
    val `type` = "RnaComponent.type"
    val type_uri = lookup(`type`)

    val componentType_value = "RnaComponent.componentType_value"
    val componentType_value_uri = lookup(componentType_value)
  }

  object rnaSequence {
    val `type` = "RnaSequence.type"
    val type_uri = lookup(`type`)
  }

  object genericOrientedSequenceComponent {
    val `type` = "GenericOrientedSequenceComponent.type"
    val type_uri = lookup(`type`)
  }
}