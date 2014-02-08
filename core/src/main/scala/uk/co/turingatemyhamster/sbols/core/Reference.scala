package uk.co.turingatemyhamster.sbols.core

import java.{net => jn}
import uk.co.turingatemyhamster.rdfPickler.ResourceMaker

/**
 *
 *
 * @author Matthew Pocock
 */
case class Reference[T](identity: jn.URI)

object Reference {
  implicit def referenceResourceMaker[T]: ResourceMaker[Reference[T]] =
    implicitly[ResourceMaker[jn.URI]] comap ((_: Reference[T]).identity)
}