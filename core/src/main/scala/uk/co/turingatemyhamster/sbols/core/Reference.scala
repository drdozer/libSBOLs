package uk.co.turingatemyhamster.sbols.core

import java.net.URI
import uk.co.turingatemyhamster.rdfPickler.ResourceMaker

/**
 *
 *
 * @author Matthew Pocock
 */
case class Reference[T](identity: URI)

object Reference {
  implicit def referenceResourceMaker[T]: ResourceMaker[Reference[T]] =
    implicitly[ResourceMaker[URI]] comap ((_: Reference[T]).identity)
}