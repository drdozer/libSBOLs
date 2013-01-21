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
