package uk.co.turingatemyhamster.sbols.component

import java.{net => jn}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.{Annotation, Identified, Reference, Documented}

/**
 *
 *
 * @author Matthew Pocock
 */
trait SequenceComponent[S <: Sequence, SA] extends Component {

  def functionalType: Seq[jn.URI]
  def sequence: Option[Reference[S]]
  def sequenceAnnotations: Seq[SA]
}

object SequenceComponent {
  implicit def sequenceComponentPickler[S <: Sequence, SA]
  (implicit rmP: ResourceMaker[SA], saP: RdfEntityPickler[SA])
  : RdfEntityPickler[SequenceComponent[S, SA]] = RdfEntityPickler.all(
    ((_: SequenceComponent[S, SA]).functionalType)      picklePropertyAs Vocabulary.sequenceComponent.functionType_uri,
    ((_: SequenceComponent[S, SA]).sequence)            picklePropertyAs Vocabulary.sequenceComponent.sequence_uri,
    ((_: SequenceComponent[S, SA]).sequenceAnnotations) picklePropertyAs Vocabulary.sequenceComponent.sequenceAnnotation_uri,
    ((_: SequenceComponent[S, SA]).sequenceAnnotations).pickleValue,
    implicitly[RdfEntityPickler[Component]]
  )
}

trait Sequence extends Documented {
  def primarySequence: String
}

object Sequence {
  implicit def sequencePickler: RdfEntityPickler[Sequence] = RdfEntityPickler.all(
    ((_: Sequence).primarySequence) picklePropertyAs Vocabulary.sequence.primarySequence_uri,
    implicitly[RdfEntityPickler[Documented]]
  )
}

trait SequenceAnnotation[SC] extends Identified {
  def bioStart: Int
  def bioEnd: Int

  def subComponent: Reference[SC]
}

object SequenceAnnotation {
  implicit def sequenceAnnotationPickler[SC]: RdfEntityPickler[SequenceAnnotation[SC]] = RdfEntityPickler.all(
    ((_: SequenceAnnotation[SC]).bioStart)      picklePropertyAs Vocabulary.sequenceAnnotation.bioStart_uri,
    ((_: SequenceAnnotation[SC]).bioEnd)        picklePropertyAs Vocabulary.sequenceAnnotation.bioEnd_uri,
    ((_: SequenceAnnotation[SC]).subComponent)  picklePropertyAs Vocabulary.sequenceAnnotation.subComponent_uri,
    implicitly[RdfEntityPickler[Identified]]
  )

  case class Impl[SC](identity: jn.URI,
                      annotations: Seq[Annotation],
                      bioStart: Int,
                      bioEnd: Int,
                      subComponent: Reference[SC]) extends SequenceAnnotation[SC]

  object Impl {
    implicit def implPickler[SC]: RdfEntityPickler[Impl[SC]] = RdfEntityPickler.all(
      ofType(Vocabulary.sequenceAnnotation.type_uri),
      implicitly[RdfEntityPickler[SequenceAnnotation[SC]]]
    )
  }
}

trait OrientedAnnotation[SC] extends SequenceAnnotation[SC] {
  def orientation: Orientation
}

object OrientedAnnotation {
  implicit def orientedAnnotationPickler[SC]: RdfEntityPickler[OrientedAnnotation[SC]] = RdfEntityPickler.all(
    ((_ : OrientedAnnotation[SC]).orientation)  picklePropertyAs Vocabulary.orientedAnnotation.orientation_uri,
    implicitly[RdfEntityPickler[SequenceAnnotation[SC]]]
  )

  case class Impl[SC](identity: jn.URI,
                  annotations: Seq[Annotation],
                  bioStart: Int,
                  bioEnd: Int,
                  subComponent: Reference[SC],
                  orientation: Orientation) extends OrientedAnnotation[SC]

  object Impl {
    implicit def implPickler[SC]: RdfEntityPickler[Impl[SC]] = RdfEntityPickler.all(
      ofType(Vocabulary.orientedAnnotation.type_uri),
      implicitly[RdfEntityPickler[OrientedAnnotation[SC]]]
    )
  }
}

sealed trait Orientation
case object Inline extends Orientation
case object ReverseComplement extends Orientation

object Orientation {
  implicit val orientationResourceMaker: ResourceMaker[Orientation] = implicitly[ResourceMaker[jn.URI]] comap {
    case Inline => Vocabulary.orientation.inline_uri
    case ReverseComplement => Vocabulary.orientation.reverseComplement_uri
  }
}
