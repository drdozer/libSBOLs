package uk.co.turingatemyhamster.sbols.component

import java.{net => jn}
import uk.co.turingatemyhamster.sbols.core.{Annotation, TopLevelEntity, Reference}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.rdfPickler
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider
import uk.co.turingatemyhamster.sbols.validation.Validator

/**
 *
 *
 * @author Matthew Pocock
 */
case class DnaComponent(identity: jn.URI,
                        annotations: Seq[Annotation] = Seq(),
                        name: Option[String] = None,
                        description: Option[String] = None,
                        displayId: Option[String] = None,
                        functionalType: Seq[jn.URI] = Seq(),
                        sequence: Option[Reference[DnaSequence]] = None,
                        sequenceAnnotations: Seq[OrientedAnnotation.Impl[DnaComponent]] = Seq())
  extends SequenceComponent[DnaSequence, OrientedAnnotation.Impl[DnaComponent]]
  with TopLevelEntity
{
  def componentType: jn.URI = Vocabulary.dnaComponent.componentType_value_uri
}

object DnaComponent {
  implicit val dnaComponentPickler: RdfEntityPickler[DnaComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.dnaComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[DnaSequence, OrientedAnnotation.Impl[DnaComponent]]
  )

  implicit val dnaComponentValidator: Validator[DnaComponent] =
    implicitly[Validator[SequenceComponent[DnaSequence, OrientedAnnotation.Impl[DnaComponent]]]]
}

class DnaComponentProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.dnaComponent.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[DnaComponent]].safeCast[TopLevelEntity]
  override def prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}

case class DnaSequence(identity: jn.URI,
                       annotations: Seq[Annotation] = Seq(),
                       name: Option[String] = None,
                       description: Option[String] = None,
                       displayId: Option[String] = None,
                       primarySequence: String) extends Sequence with TopLevelEntity

object DnaSequence {
  implicit val dnaSequencePickler: RdfEntityPickler[DnaSequence] = RdfEntityPickler.all(
    ofType(Vocabulary.dnaSequence.type_uri),
    implicitly[RdfEntityPickler[Sequence]]
  )

  implicit val dnaSequenceValidator: Validator[DnaSequence] =
    implicitly[Validator[Sequence]]
}

class DnaSequenceProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.dnaSequence.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[DnaSequence]].safeCast[TopLevelEntity]
  override def prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}