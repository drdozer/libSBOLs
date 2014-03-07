package uk.co.turingatemyhamster.sbols.core

import java.{net => jn}

/**
 *
 *
 * @author Matthew Pocock
 */
object URI {
  def apply(uri: String): jn.URI = new jn.URI(uri)
  def unapply(uri: jn.URI): Option[String] = Some(uri.toString)
}
