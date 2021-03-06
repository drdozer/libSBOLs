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
trait Instantiation[I <: Identified] {
  self : Identified =>

  private def _self: Identified = self
  def instantiated: jn.URI
}

object Instantiation {
  private implicit def instantiationResourceMaker[I <: Identified]: ResourceMaker[Instantiation[I]] =
    implicitly[ResourceMaker[Identified]] comap ((_: Instantiation[I])._self)

  implicit def instantiationPickler[I <: Identified]: RdfEntityPickler[Instantiation[I]] =
    ((_: Instantiation[I]).instantiated) picklePropertyAs Vocabulary.instantiation.instantiated_uri

  implicit def intantiationValidator[I <: Identified]: Validator[Instantiation[I]] =
    ((_: Instantiation[I]).instantiated) as "instantiated" validateWith notNull
}