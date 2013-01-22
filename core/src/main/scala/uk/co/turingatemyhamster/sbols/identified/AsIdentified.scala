package uk.co.turingatemyhamster.sbols
package identified

import java.net.URI

/**
 * Abstract data representation of something that is identified by a URI.
 * The URI uniquely identifies the resource, although there may be
 * several instances with this URI that provide information about that same resource.
 *
 * @tparam I  the underlying type that is identified
 */
trait AsIdentified[I] {
  /**
   * Retrieve the identifying URI of an identified instance.
   *
   * @param i the instance
   * @return  the identifying URI of this instance
   */
  def uri(i: I): URI
}

object AsIdentified {
  @inline def asIdentified[I](i: I)(implicit as : AsIdentified[I]) = as

  implicit class IdentifiedOps[I](val i: I) extends AnyVal {
    @inline def sameEntity[J](j: J)(implicit iAs : AsIdentified[I], jAs : AsIdentified[J]) = iAs.uri(i) == jAs.uri(j)
  }
}