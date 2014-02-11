package uk.co.turingatemyhamster.sbols.core

import java.{net => jn}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.validation._
import Validator._

/**
 *
 *
 * @author Matthew Pocock
 */
case class Port[P <: Identified](identity: jn.URI,
                                 annotations: Seq[Annotation] = Seq(),
                                 exposes: Reference[P],
                                 directionality: Directionality
                                  ) extends Identified

object Port {
  implicit def portPickler[P <: Identified]: RdfEntityPickler[Port[P]] = RdfEntityPickler.all(
    ofType(Vocabulary.port.type_uri),
    ((_: Port[P]).exposes)         picklePropertyAs Vocabulary.port.exposes_uri,
    ((_: Port[P]).directionality)  picklePropertyAs Vocabulary.port.directionality_uri,
    implicitly[RdfEntityPickler[Identified]]
  )

  implicit def portValidator[P <: Identified]: Validator[Port[P]] =
    (((_: Port[P]).directionality) as "directionality" validateWith notNull) |&&|
      implicitly[Validator[Identified]]
}

sealed trait Directionality
case object IN     extends Directionality
case object OUT    extends Directionality
case object IN_OUT extends Directionality

object Directionality {
  implicit def directionalityResourceMaker: ResourceMaker[Directionality] = implicitly[ResourceMaker[jn.URI]] comap {
    case IN     => Vocabulary.directionality.in_uri
    case OUT    => Vocabulary.directionality.out_uri
    case IN_OUT => Vocabulary.directionality.in_out_uri
  }
}