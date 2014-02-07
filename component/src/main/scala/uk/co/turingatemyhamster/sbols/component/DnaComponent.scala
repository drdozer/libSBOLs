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
                        sequenceAnnotations: DnaAnnotation)
  extends SequenceComponent[DnaSequence, OrientedAnnotation[SequenceComponent[DnaSequence, DnaComponent]]]
  with TopLevelEntity

object DnaComponent {
  implicit def dnaComponentPickler: RdfEntityPickler[DnaComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.dnaComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[DnaSequence, DnaAnnotation]
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

case class DnaAnnotation(identity: URI,
                         annotations: Seq[Annotation],
                         bioStart: Int,
                         bioEnd: Int,
                         subComponent: Reference[DnaComponent],
                         orientation: Orientation) extends OrientedAnnotation[DnaComponent]

object DnaAnnotation {
  implicit def dnaAnnotationPickler: RdfEntityPickler[DnaAnnotation] =
    implicitly[RdfEntityPickler[OrientedAnnotation[DnaComponent]]]
}