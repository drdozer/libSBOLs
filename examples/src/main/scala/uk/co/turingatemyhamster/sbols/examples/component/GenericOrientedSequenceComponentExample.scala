package uk.co.turingatemyhamster.sbols.examples.component

import uk.co.turingatemyhamster.sbols.component._
import uk.co.turingatemyhamster.sbols.core.{Reference, SbolDocument, URI}
import java.io.StringWriter
import scala.Some

/**
 *
 *
 * @author Matthew Pocock
 */
object GenericOrientedSequenceComponentExample {
  def main(args: Array[String]) {
    val gs1 = GenericSequence(
      identity = URI("http://turingatemyhamster.co.uk/example#gs1"),
      primarySequence = "xooxoxxo",
      legalCharacters = "xo"
    )

    val gc1 = GenericOrientedSequenceComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#gc1"),
        name = Some("Generic Oriented Component 1"),
        description = Some("My first oriented Polymer Component"),
        displayId = Some("gc1"),
        componentType = URI("http://turingatemyhamster.co.uk/example#random_oriented_polymer"),
        sequence = Some(Reference(gs1.identity))
    )

    val gc2 = GenericOrientedSequenceComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#gc2"),
      name = Some("Generic Oriented Component 2"),
      description = Some("My second oriented Component"),
      displayId = Some("rc2"),
      componentType = URI("http://turingatemyhamster.co.uk/example#random_oriented_polymer"),
      sequenceAnnotations = Seq(
        OrientedAnnotation.Impl(
          identity = URI("http://turingatemyhamter.co.uk/example#gc2/sa1"),
          bioStart = Some(20),
          bioEnd = Some(32),
          subComponent = Reference(gc1.identity),
          orientation = Inline
        )
      )
    )

    val doc = SbolDocument.Impl(Seq(gs1, gc1, gc2))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
