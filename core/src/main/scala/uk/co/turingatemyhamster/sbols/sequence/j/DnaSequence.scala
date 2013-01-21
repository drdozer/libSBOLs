package uk.co.turingatemyhamster.sbols
package sequence
package j

import java.net.URI
import identified.j.Identified

trait DnaSequence extends Identified {
  def getNucleotides: String
  def setNucleotides(nucleotides: String)
}

object DnaSequence {
  trait DnaSequenceAsDnaSequence[DS <: DnaSequence] extends AsDnaSequence[DS] with Identified.IdentifiedAsIdentified[DS] {
    def nucleotides(ds: DS) = ds.getNucleotides
  }
  implicit object dnaSequenceAsDnaSequence extends DnaSequenceAsDnaSequence[DnaSequence] {}
}

case class DnaSequenceImpl(private var uri: URI, private var nucleotides: String) extends DnaSequence {
  def getNucleotides = nucleotides
  def setNucleotides(nucleotides: String) { this.nucleotides = nucleotides }

  def getURI = uri
  def setURI(uri: URI) { this.uri = uri }
}