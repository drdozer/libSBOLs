package uk.co.turingatemyhamster.sbols.core.spi

import uk.co.turingatemyhamster.rdfPickler.RdfEntityPickler
import uk.co.turingatemyhamster.sbols.core.TopLevelEntity
import java.net.URI

/**
 *
 *
 * @author Matthew Pocock
 */
trait TopLevelEntryProvider {
  def pickler: RdfEntityPickler[TopLevelEntity]
  def uri: URI
}
