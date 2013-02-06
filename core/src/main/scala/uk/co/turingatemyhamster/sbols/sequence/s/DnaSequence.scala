package uk.co.turingatemyhamster.sbols
package sequence
package s

import identified.s.Identified
import java.net.URI

trait DnaSequence extends Identified {
  def nucleotides: String
}

object DnaSequence {
  abstract class DnaSequenceAsDnaSequence[DS <: DnaSequence]
    extends Identified.IdentifiedAsIdentified[DS]
    with AsDnaSequence[DS]
  {
    def nucleotides(ds: DS) = ds.nucleotides
  }

  implicit def dnaSequenceAsDnaSequence[DS <: DnaSequence]: DnaSequenceAsDnaSequence[DS] =
    new DnaSequenceAsDnaSequence[DS] {}
}

case class DnaSequenceImpl(uri: URI, nucleotides: String) extends DnaSequence
