package uk.co.turingatemyhamster.sbols
package identified
package s

import java.net.URI

trait Described extends Identified {

  /**
   * The display ID
   *
   * @return the display ID
   */
  def displayId: String

  /**
   * A recognisable name for this instance.
   *
   * @return  an optional name
   */
  def name: Option[String]

  /**
   * A human-readable description for this instance.
   *
   * @return  an optional description
   */
  def description: Option[String]

}

object Described {

  abstract class DescribedAsDescribed[D <: Described]
    extends Identified.IdentifiedAsIdentified[D]
    with AsDescribed[D]
  {
    final def displayId(d: D) = d.displayId
    final def name(d: D) = d.name
    final def description(d: D) = d.description
  }

  implicit object describedAsDescribed
}

case class DescribedImpl(uri: URI, displayId: String, name: Option[String], description: Option[String]) extends Described