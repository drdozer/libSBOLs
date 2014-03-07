package uk.co.turingatemyhamster.sbols.examples.component

import uk.co.turingatemyhamster.sbols.component.{ProteinSequence, RnaSequence, DnaSequence, GenericSequence}
import uk.co.turingatemyhamster.sbols.core.{SbolDocument, URI}
import java.io.StringWriter

/**
 *
 *
 * @author Matthew Pocock
 */
object MultipleSequencesExample {
  def main(args: Array[String]) {
    val gs = GenericSequence(
      identity = URI("http://turingatemyhamster.co.uk/example/gs"),
      primarySequence = "xooxo",
      legalCharacters = "xo"
    )

    val ds = DnaSequence(
      identity = URI("http://turingatemyhamster.co.uk/example/dnaSeq"),
      primarySequence = "aggactacgt"
    )

    val rs = RnaSequence(
      identity = URI("http://turingatemyhamster.co.uk/example/rnaSeq"),
      primarySequence = "augcguaccguacgaag"
    )

    val ps = ProteinSequence(
      identity = URI("http://turingatemyhamster.co.uk/example/protSeq"),
      primarySequence = "lkdfgasfa"
    )

    val doc = SbolDocument.Impl(Seq(gs, ds, rs, ps))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
