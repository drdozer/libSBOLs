package uk.co.turingatemyhamster.sbols
package sequence
package s

import identified.s.Identified
import java.net.URI

trait DnaSequence extends Identified {
  def nucleotides: String
}

object DnaSequence {
  trait DnaSequenceAsDnaSequence[DS <: DnaSequence] extends AsDnaSequence[DS] with Identified.IdentifiedAsIdentified[DS] {
    def nucleotides(ds: DS) = ds.nucleotides
  }
  implicit object dnaSequenceAsDnaSequence extends DnaSequenceAsDnaSequence[DnaSequence] {}
}

case class DnaSequenceImpl(uri: URI, nucleotides: String) extends DnaSequence
