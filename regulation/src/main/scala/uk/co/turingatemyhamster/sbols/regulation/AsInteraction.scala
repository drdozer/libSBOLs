package uk.co.turingatemyhamster.sbols
package regulation

import java.net.URI
import identified.AsIdentified

trait AsInteraction[I, Pl] extends AsIdentified[I] {
  def players(i: I): Set[Pl]
  def `type`(i: I): URI
}

trait AsPlayer[Pl, P] extends AsIdentified[Pl] {
  def port(pl: Pl): P
  def role(pl: Pl): URI
}

