package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.Annotation
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider
import uk.co.turingatemyhamster.sbols.validation.Validator

/**
 *
 *
 * @author Matthew Pocock
 */
case class ProteinComponent(identity: URI,
                        annotations: Seq[Annotation] = Seq(),
                        name: Option[String] = None,
                        description: Option[String] = None,
                        displayId: Option[String] = None,
                        functionalType: Seq[URI] = Seq(),
                        sequence: Option[Reference[ProteinSequence]] = None,
                        sequenceAnnotations: Seq[SequenceAnnotation.Impl[ProteinComponent]] = Seq())
  extends SequenceComponent[ProteinSequence, SequenceAnnotation.Impl[ProteinComponent]]
  with TopLevelEntity
{
  def componentType: URI = Vocabulary.proteinComponent.componentType_value_uri
}

object ProteinComponent {
  implicit val proteinComponentPickler: RdfEntityPickler[ProteinComponent] = RdfEntityPickler.all(
    ofType(Vocabulary.proteinComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[ProteinSequence, SequenceAnnotation.Impl[ProteinComponent]]
  )

  implicit val proteinComponentValidator: Validator[ProteinComponent] =
    implicitly[Validator[SequenceComponent[ProteinSequence, SequenceAnnotation.Impl[ProteinComponent]]]]
}

class ProteinComponentProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.proteinComponent.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[ProteinComponent]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}

case class ProteinSequence(identity: URI,
                       annotations: Seq[Annotation] = Seq(),
                       name: Option[String] = None,
                       description: Option[String] = None,
                       displayId: Option[String] = None,
                       primarySequence: String) extends Sequence with TopLevelEntity

object ProteinSequence {
  implicit val proteinSequencePickler: RdfEntityPickler[ProteinSequence] = RdfEntityPickler.all(
    ofType(Vocabulary.proteinSequence.type_uri),
    implicitly[RdfEntityPickler[Sequence]]
  )

  implicit val proteinSequenceValidator: Validator[ProteinSequence] =
    implicitly[Validator[Sequence]]
}

class ProteinSequenceProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.proteinSequence.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[ProteinSequence]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}