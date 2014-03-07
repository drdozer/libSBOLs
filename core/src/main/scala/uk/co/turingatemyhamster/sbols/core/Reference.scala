package uk.co.turingatemyhamster.sbols.core

import java.{net => jn}
import uk.co.turingatemyhamster.sbols.rdfPickler.ResourceMaker
import uk.co.turingatemyhamster.sbols.validation._
import Validator._

/**
 *
 *
 * @author Matthew Pocock
 */
case class Reference[+T](identity: jn.URI)

object Reference {

  def apply[I <: Identified](i: I): Reference[I] = Reference(i.identity)

  implicit def referenceResourceMaker[T]: ResourceMaker[Reference[T]] =
    implicitly[ResourceMaker[jn.URI]] comap ((_: Reference[T]).identity)

  implicit def referenceValidator[T]: Validator[Reference[T]] =
    ((_: Reference[T]).identity) as "identity" validateWith notNull
}