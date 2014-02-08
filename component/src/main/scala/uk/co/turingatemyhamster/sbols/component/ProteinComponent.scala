package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.rdfPickler._
import uk.co.turingatemyhamster.rdfPickler
import uk.co.turingatemyhamster.sbols.core.Annotation

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
                        componentType: URI,
                        functionalType: Seq[URI],
                        sequence: Option[Reference[ProteinSequence]],
                        sequenceAnnotations: Seq[SequenceAnnotation[ProteinComponent]])
  extends SequenceComponent[ProteinSequence, SequenceAnnotation[ProteinComponent]]
  with TopLevelEntity

object ProteinComponent {
  implicit def proteinComponentPickler: RdfEntityPickler[ProteinComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.proteinComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[ProteinSequence, SequenceAnnotation[ProteinComponent]]
  )
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