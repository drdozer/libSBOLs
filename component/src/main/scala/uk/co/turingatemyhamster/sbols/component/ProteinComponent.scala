package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.Annotation
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

/**
 *
 *
 * @author Matthew Pocock
 */
case class ProteinComponent(identity: URI,
                        annotations: Seq[Annotation],
                        name: Option[String],
                        description: Option[String],
                        displayId: Option[String],
                        functionalType: Seq[URI],
                        sequence: Option[Reference[ProteinSequence]],
                        sequenceAnnotations: Seq[SequenceAnnotation.Impl[ProteinComponent]])
  extends SequenceComponent[ProteinSequence, SequenceAnnotation.Impl[ProteinComponent]]
  with TopLevelEntity
{
  def componentType: URI = Vocabulary.proteinComponent.componentType_value_uri
}

object ProteinComponent {
  implicit def proteinComponentPickler: RdfEntityPickler[ProteinComponent] = RdfEntityPickler.all(
    ofType(Vocabulary.proteinComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[ProteinSequence, SequenceAnnotation.Impl[ProteinComponent]]
  )
}

class ProteinComponentProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.proteinComponent.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[ProteinComponent]].safeCast[TopLevelEntity]
}

case class ProteinSequence(identity: URI,
                       annotations: Seq[Annotation],
                       name: Option[String],
                       description: Option[String],
                       displayId: Option[String],
                       primarySequence: String) extends Sequence with TopLevelEntity

object ProteinSequence {
  implicit def proteinSequencePickler: RdfEntityPickler[ProteinSequence] = RdfEntityPickler.all(
    ofType(Vocabulary.proteinSequence.type_uri),
    implicitly[RdfEntityPickler[Sequence]]
  )
}

class ProteinSequenceProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.proteinSequence.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[ProteinSequence]].safeCast[TopLevelEntity]
}