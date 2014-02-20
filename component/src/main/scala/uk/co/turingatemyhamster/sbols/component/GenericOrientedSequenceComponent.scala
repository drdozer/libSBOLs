package uk.co.turingatemyhamster.sbols.component

import java.net.URI
import uk.co.turingatemyhamster.sbols.core.{TopLevelEntity, Reference, Annotation}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.rdfPickler
import uk.co.turingatemyhamster.sbols.validation.Validator
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

case class GenericOrientedSequenceComponent(identity: URI,
                        annotations: Seq[Annotation] = Seq(),
                        name: Option[String] = None,
                        description: Option[String] = None,
                        displayId: Option[String] = None,
                        componentType: URI,
                        functionalType: Seq[URI] = Seq(),
                        sequence: Option[Reference[GenericSequence]] = None,
                        sequenceAnnotations: Seq[OrientedAnnotation.Impl[GenericOrientedSequenceComponent]] = Seq()
                         ) extends SequenceComponent[GenericSequence, OrientedAnnotation.Impl[GenericOrientedSequenceComponent]]
                           with TopLevelEntity

object GenericOrientedSequenceComponent {

  implicit val genericOrientedSequenceComponentPickler: RdfEntityPickler[GenericOrientedSequenceComponent] = RdfEntityPickler.all(
    rdfPickler.ofType(Vocabulary.genericOrientedSequenceComponent.type_uri),
    SequenceComponent.sequenceComponentPickler[GenericSequence, OrientedAnnotation.Impl[GenericOrientedSequenceComponent]]
  )

  implicit val genericOrientedSequenceComponentValidator: Validator[GenericOrientedSequenceComponent] =
    implicitly[Validator[SequenceComponent[GenericSequence, OrientedAnnotation.Impl[GenericOrientedSequenceComponent]]]]
}

class GenericOrientedSequenceComponentProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.genericOrientedSequenceComponent.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[GenericOrientedSequenceComponent]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}
