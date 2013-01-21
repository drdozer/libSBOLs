package uk.co.turingatemyhamster.sbols
package identified

/**
 * An entity that is described by an identifier, name and description. This corresponds to the appropriate fields of
 * Dublin Core.
 *
 * @author Matthew Pocock
 */
trait AsDescribed[D] extends AsIdentified[D] {

  /**
   * The display ID
   *
   * @return the display ID
   */
  def displayId(d: D): String

  /**
   * A recognisable name for this instance.
   *
   * @return  an optional name
   */
  def name(d: D): Option[String]

  /**
   * A human-readable description for this instance.
   *
   * @return  an optional description
   */
  def description(d: D): Option[String]

}
