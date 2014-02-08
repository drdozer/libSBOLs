package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.rdfPickler._
import uk.co.turingatemyhamster.rdfPickler
import uk.co.turingatemyhamster.sbols.core.Annotation
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

case class RnaComponent(identity: URI,
                        annotations: Seq[Annotation],
                        name: Option[String],
                        description: Option[String],
                        displayId: Option[String],
                        functionalType: Seq[URI],
                        sequence: Option[Reference[RnaSequence]],
                        sequenceAnnotations: Seq[OrientedAnnotation.Impl[RnaComponent]])
  extends SequenceComponent[RnaSequence, OrientedAnnotation.Impl[RnaComponent]]
  with TopLevelEntity
{
  def componentType: URI = Vocabulary.rnaComponent.componentType_value_uri
}

object RnaComponent {

  implicit def dnaComponentPickler: RdfEntityPickler[RnaComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.rnaComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[RnaSequence, OrientedAnnotation.Impl[RnaComponent]]
  )
}

class RnaComponentProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.rnaComponent.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[RnaComponent]].safeCast[TopLevelEntity]
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

class RnaSequenceProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.rnaSequence.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[RnaSequence]].safeCast[TopLevelEntity]
}