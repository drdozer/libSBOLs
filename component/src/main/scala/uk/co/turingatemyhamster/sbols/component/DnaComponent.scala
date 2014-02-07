package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import scala.annotation.Annotation
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference}
import uk.co.turingatemyhamster.rdfPickler._
import uk.co.turingatemyhamster.rdfPickler

/**
 *
 *
 * @author Matthew Pocock
 */
case class DnaComponent(identity: URI,
                        annotations: Seq[Annotation],
                        name: Option[String],
                        description: Option[String],
                        displayId: Option[String],
                        componentType: URI,
                        functionalType: URI,
                        sequence: Reference[DnaSequence],
                        sequenceAnnotations: DnaComponent.DnaAnnotation)
  extends SequenceComponent[DnaSequence, OrientedAnnotation[SequenceComponent[DnaSequence, DnaComponent]]]
  with TopLevelEntity

object DnaComponent {
  type DnaAnnotation = OrientedAnnotation[DnaComponent]

  implicit def dnaComponentPickler: RdfEntityPickler[DnaComponent] = RdfEntityPickler.all(
    sandwiches(Vocabulary.dnaComponent.type_uri),
    rdfPickler.ofType(Vocabulary.dnaComponent.type_uri),
    implicitly[RdfEntityPickler[SequenceComponent[DnaSequence, OrientedAnnotation[DnaComponent]]]]
  )
}

case class DnaSequence(identity: URI,
                       annotations: Seq[Annotation],
                       name: Option[String],
                       description: Option[String],
                       displayId: Option[String],
                       primarySequence: String) extends Sequence with TopLevelEntity

object DnaSequence {
  implicit def dnaSequencePickler: RdfEntityPickler[DnaSequence] = RdfEntityPickler.all(
    ofType(Vocabulary.dnaSequence.type_uri),
    implicitly[RdfEntityPickler[Sequence]]
  )
}