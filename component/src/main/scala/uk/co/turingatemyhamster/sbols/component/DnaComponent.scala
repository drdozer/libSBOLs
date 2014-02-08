package uk.co.turingatemyhamster.sbols.component

import java.{net => jn}
import uk.co.turingatemyhamster.sbols.core.{Annotation, TopLevelEntity, Reference}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.rdfPickler
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

/**
 *
 *
 * @author Matthew Pocock
 */
case class DnaComponent(identity: jn.URI,
                        annotations: Seq[Annotation],
                        name: Option[String],
                        description: Option[String],
                        displayId: Option[String],
                        functionalType: Seq[jn.URI],
                        sequence: Option[Reference[DnaSequence]],
                        sequenceAnnotations: Seq[OrientedAnnotation.Impl[DnaComponent]])
  extends SequenceComponent[DnaSequence, OrientedAnnotation.Impl[DnaComponent]]
  with TopLevelEntity
{
  def componentType: jn.URI = Vocabulary.dnaComponent.componentType_value_uri
}

object DnaComponent {
  implicit def dnaComponentPickler: RdfEntityPickler[DnaComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.dnaComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[DnaSequence, OrientedAnnotation.Impl[DnaComponent]]
  )

  def tle(dc: DnaComponent): TopLevelEntity = dc
}

class DnaComponentProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.dnaComponent.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[DnaComponent]].safeCast[TopLevelEntity]
}

case class DnaSequence(identity: jn.URI,
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

class DnaSequenceProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.dnaSequence.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[DnaSequence]].safeCast[TopLevelEntity]
}