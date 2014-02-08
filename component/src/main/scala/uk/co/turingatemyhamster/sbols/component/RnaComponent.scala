package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.rdfPickler._
import uk.co.turingatemyhamster.rdfPickler
import uk.co.turingatemyhamster.sbols.core.Annotation

case class RnaComponent(identity: URI,
                        annotations: Seq[Annotation],
                        name: Option[String],
                        description: Option[String],
                        displayId: Option[String],
                        componentType: URI,
                        functionalType: Seq[URI],
                        sequence: Option[Reference[RnaSequence]],
                        sequenceAnnotations: Seq[OrientedAnnotation[RnaComponent]])
  extends SequenceComponent[RnaSequence, OrientedAnnotation[RnaComponent]]
  with TopLevelEntity

object RnaComponent {

  implicit def dnaComponentPickler: RdfEntityPickler[RnaComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.rnaComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[RnaSequence, OrientedAnnotation[RnaComponent]]
  )
}

case class RnaSequence(identity: URI,
                       annotations: Seq[Annotation],
                       name: Option[String],
                       description: Option[String],
                       displayId: Option[String],
                       primarySequence: String) extends Sequence with TopLevelEntity

object RnaSequence {
  implicit def dnaSequencePickler: RdfEntityPickler[RnaSequence] = RdfEntityPickler.all(
    ofType(Vocabulary.dnaSequence.type_uri),
    implicitly[RdfEntityPickler[Sequence]]
  )
}