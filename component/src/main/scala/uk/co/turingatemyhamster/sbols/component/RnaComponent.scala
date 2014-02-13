package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.rdfPickler
import uk.co.turingatemyhamster.sbols.core.Annotation
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider
import uk.co.turingatemyhamster.sbols.validation.Validator

case class RnaComponent(identity: URI,
                        annotations: Seq[Annotation] = Seq(),
                        name: Option[String] = None,
                        description: Option[String] = None,
                        displayId: Option[String] = None,
                        functionalType: Seq[URI] = Seq(),
                        sequence: Option[Reference[RnaSequence]] = None,
                        sequenceAnnotations: Seq[OrientedAnnotation.Impl[RnaComponent]] = Seq()
                         ) extends SequenceComponent[RnaSequence, OrientedAnnotation.Impl[RnaComponent]]
                           with TopLevelEntity
{
  def componentType: URI = Vocabulary.rnaComponent.componentType_value_uri
}

object RnaComponent {

  implicit val rnaComponentPickler: RdfEntityPickler[RnaComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.rnaComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[RnaSequence, OrientedAnnotation.Impl[RnaComponent]]
  )

  implicit val rnaComponentValidator: Validator[RnaComponent] =
    implicitly[Validator[SequenceComponent[RnaSequence, OrientedAnnotation.Impl[RnaComponent]]]]
}

class RnaComponentProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.rnaComponent.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[RnaComponent]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}

case class RnaSequence(identity: URI,
                       annotations: Seq[Annotation] = Seq(),
                       name: Option[String] = None,
                       description: Option[String] = None,
                       displayId: Option[String] = None,
                       primarySequence: String
                        ) extends Sequence with TopLevelEntity

object RnaSequence {
  implicit val rnaSequencePickler: RdfEntityPickler[RnaSequence] = RdfEntityPickler.all(
    ofType(Vocabulary.rnaSequence.type_uri),
    implicitly[RdfEntityPickler[Sequence]]
  )

  implicit val rnaSequenceValidator: Validator[RnaSequence] =
    implicitly[Validator[Sequence]]
}

class RnaSequenceProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.rnaSequence.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[RnaSequence]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}