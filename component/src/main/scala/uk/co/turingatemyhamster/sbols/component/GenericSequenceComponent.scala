package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.validation.Validator
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

/**
 *
 *
 * @author Matthew Pocock
 */
case class GenericSequenceComponent(identity: URI,
                            annotations: Seq[Annotation] = Seq(),
                            name: Option[String] = None,
                            description: Option[String] = None,
                            displayId: Option[String] = None,
                            componentType: URI,
                            functionalType: Seq[URI] = Seq(),
                            sequence: Option[Reference[GenericSequence]] = None,
                            sequenceAnnotations: Seq[SequenceAnnotation.Impl[GenericSequenceComponent]] = Seq())
  extends SequenceComponent[GenericSequence, SequenceAnnotation.Impl[GenericSequenceComponent]]
  with TopLevelEntity

object GenericSequenceComponent {
  implicit val genericSequenceComponentPickler: RdfEntityPickler[GenericSequenceComponent] = RdfEntityPickler.all(
    ofType(Vocabulary.genericSequenceComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[GenericSequence, SequenceAnnotation.Impl[GenericSequenceComponent]]
  )

  implicit val genericSequenceComponentValidator: Validator[GenericSequenceComponent] =
    implicitly[Validator[SequenceComponent[GenericSequence, SequenceAnnotation.Impl[GenericSequenceComponent]]]]
}

class GenericSequenceComponentProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.genericSequenceComponent.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[GenericSequenceComponent]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}

case class GenericSequence(identity: URI,
                       annotations: Seq[Annotation] = Seq(),
                       name: Option[String] = None,
                       description: Option[String] = None,
                       displayId: Option[String] = None,
                       primarySequence: String,
                       legalCharacters: String) extends Sequence with TopLevelEntity

object GenericSequence {
  implicit val genericSequencePickler: RdfEntityPickler[GenericSequence] = RdfEntityPickler.all(
    ofType(Vocabulary.genericSequenceComponent.type_uri),
    ((_: GenericSequence).legalCharacters) picklePropertyAs Vocabulary.genericSequence.legalCharacters_uri,
    implicitly[RdfEntityPickler[Sequence]]
  )

  implicit val genericSequenceValidator: Validator[GenericSequence] =
    implicitly[Validator[Sequence]]
}

class GenericSequenceProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.genericSequenceComponent.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[GenericSequence]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}