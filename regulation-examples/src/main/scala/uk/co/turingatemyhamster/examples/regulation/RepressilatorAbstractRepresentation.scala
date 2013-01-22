package uk.co.turingatemyhamster
package examples.regulation

import sbols.sequence.s.{DnaSequence, SequenceAnnotation, DnaComponent, DnaComponentImpl}
import java.net.URI

/**
 * An example to demonstrate representing the Repressilator.
 *
 * @author Matthew Pocock
 */
object RepressilatorAbstractRepresentation {
  type DC = DnaComponent[DnaSequence, SequenceAnnotation.Canonical]

  def main(args: Array[String]) {

    val plLac01 : DC = DnaComponent()
      .uri("usecase:repressilator/PLlac01")
      .displayId("PLlac01")
      .sequenceType("so:promoter")

    val tetR_lite : DC = DnaComponent()
      .uri("usecase:repressilator/tetR-lite")
      .displayId("tetR-lite")
      .sequenceType("so:CDS")


  }

  private implicit def URI(s: String): URI = new URI(s) // convenience
}
