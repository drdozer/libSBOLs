package uk.co.turingatemyhamster.sbols.component

import java.{net => jn}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.{Annotation, Identified, Reference, Documented}
import uk.co.turingatemyhamster.validation._
import Validator._

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

  implicit def sequenceComponentValidator[S <: Sequence, SA]
  (implicit saV: Validator[SA]): Validator[SequenceComponent[S, SA]] =
    (((_: SequenceComponent[S, SA]).functionalType) as "functionalType" validateWith notNull) |&&|
      (((_: SequenceComponent[S, SA]).sequence) as "sequence" validateWith(isNone |<>| isSome(implicitly[Validator[Reference[S]]]))) |&&|
      (((_: SequenceComponent[S, SA]).sequenceAnnotations) as "sequenceAnnotations" validateWith each(notNull |&&| saV)) |&&|
      implicitly[Validator[Component]]
}

trait Sequence extends Documented {
  def primarySequence: String
}

object Sequence {
  implicit def sequencePickler: RdfEntityPickler[Sequence] = RdfEntityPickler.all(
    ((_: Sequence).primarySequence) picklePropertyAs Vocabulary.sequence.primarySequence_uri,
    implicitly[RdfEntityPickler[Documented]]
  )

  implicit def sequenceValidator: Validator[Sequence] =
    (((_: Sequence).primarySequence) as "primarySequence" validateWith notNull) |&&|
      implicitly[Validator[Documented]]
}

trait SequenceAnnotation[SC] extends Identified {
  def bioStart: Int
  def bioEnd: Int

  def subComponent: Reference[SC]
}

object SequenceAnnotation {
  implicit def sequenceAnnotationPickler[SC]: RdfEntityPickler[SequenceAnnotation[SC]] = RdfEntityPickler.all(
    ((_: SequenceAnnotation[SC]).subComponent)  picklePropertyAs Vocabulary.sequenceAnnotation.subComponent_uri,
    ((_: SequenceAnnotation[SC]).bioEnd)        picklePropertyAs Vocabulary.sequenceAnnotation.bioEnd_uri,
    ((_: SequenceAnnotation[SC]).bioStart)      picklePropertyAs Vocabulary.sequenceAnnotation.bioStart_uri,
    implicitly[RdfEntityPickler[Identified]]
  )

  implicit def sequenceAnnotationValidator[SC]: Validator[SequenceAnnotation[SC]] =
    (((_: SequenceAnnotation[SC]).subComponent) as "subComponent" validateWith (notNull |&&| implicitly[Validator[Reference[SC]]])) |&&|
      (((_: SequenceAnnotation[SC]).bioStart) as "bioStart" validateWith (notNull |&&| gteq(1))) |&&|
      (((_: SequenceAnnotation[SC]).bioEnd) as "bioEnd" validateWith (notNull |&&| gteq(1))) |&&|
      (((sa: SequenceAnnotation[SC]) => (sa.bioStart, sa.bioEnd)) as "(bioStart, bioEnd)" validateWith lteq_pair) |&&|
      implicitly[Validator[Identified]]

  case class Impl[SC](identity: jn.URI,
                      annotations: Seq[Annotation] = Seq(),
                      bioStart: Int,
                      bioEnd: Int,
                      subComponent: Reference[SC]) extends SequenceAnnotation[SC]

  object Impl {
    implicit def implPickler[SC]: RdfEntityPickler[Impl[SC]] = RdfEntityPickler.all(
      ofType(Vocabulary.sequenceAnnotation.type_uri),
      implicitly[RdfEntityPickler[SequenceAnnotation[SC]]]
    )

    implicit def implValidator[SC]: Validator[Impl[SC]] =
      implicitly[Validator[SequenceAnnotation[SC]]]
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

  implicit def orientedAnnotationValidator[SC]: Validator[OrientedAnnotation[SC]] =
    (((_: OrientedAnnotation[SC]).orientation) as "orientation" validateWith notNull) |&&|
      implicitly[Validator[SequenceAnnotation[SC]]]

  case class Impl[SC](identity: jn.URI,
                  annotations: Seq[Annotation] = Seq(),
                  bioStart: Int,
                  bioEnd: Int,
                  subComponent: Reference[SC],
                  orientation: Orientation) extends OrientedAnnotation[SC]

  object Impl {
    implicit def implPickler[SC]: RdfEntityPickler[Impl[SC]] = RdfEntityPickler.all(
      ofType(Vocabulary.orientedAnnotation.type_uri),
      implicitly[RdfEntityPickler[OrientedAnnotation[SC]]]
    )

    implicit def implValidator[SC]: Validator[Impl[SC]] =
      implicitly[Validator[OrientedAnnotation[SC]]]
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
