package uk.co.turingatemyhamster.sbols
package identified
package s

import java.net.URI


/**
 * Any entity that is identified by a URI. All instances of this type should use this URI as their resource when
 * represented as RDF.
 *
 * @author Matthew Pocock
 */
trait Identified {
  /**
   * The identifying URI of this instance.
   *
   * @return  the identifying URI of this instance
   */
  def uri: URI
}

object Identified {
  /**
   * The `AsIdentified` instance for all types extending `Identified`.
   */
  trait IdentifiedAsIdentified[I <: Identified] extends AsIdentified[I] {
    final def uri(i: I) = i.uri
  }

  implicit object identifiedAsIdentified extends IdentifiedAsIdentified[Identified] {}
}

/**
 * Case-class implementing the `Identified` type.
 * Typically, you will not extend this, but instead directly implement a sub-trait of `Identified`.
 *
 * @param uri   the identifying `URI`
 */
case class IdentifiedImpl(uri: URI) extends Identified
