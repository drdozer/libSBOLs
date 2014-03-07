package uk.co.turingatemyhamster.sbols.examples.component

import uk.co.turingatemyhamster.sbols.component.{RnaSequence, ReverseComplement, OrientedAnnotation, RnaComponent}
import uk.co.turingatemyhamster.sbols.core.{Reference, SbolDocument, URI}
import java.io.StringWriter

/**
 *
 *
 * @author Matthew Pocock
 */
object RnaComponentExample {
  def main(args: Array[String]) {
    val rs1 = RnaSequence(
      identity = URI("http://turingatemyhamster.co.uk/example#rs1"),
      primarySequence = "uagcuagcugcu"
    )

    val rc1 = RnaComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#rc1"),
        name = Some("RNA Component 1"),
        description = Some("My first RNA Component"),
        displayId = Some("rc1"),
        sequence = Some(Reference(rs1.identity))
    )

    val rc2 = RnaComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#rc2"),
      name = Some("RNA Component 2"),
      description = Some("My second RNA Component"),
      displayId = Some("rc2"),
      sequenceAnnotations = Seq(
        OrientedAnnotation.Impl(
          identity = URI("http://turingatemyhamter.co.uk/example#rc2/sa1"),
          bioStart = Some(20),
          bioEnd = Some(32),
          subComponent = Reference(rc1.identity),
          orientation = ReverseComplement
        )
      )
    )

    val doc = SbolDocument.Impl(Seq(rs1, rc1, rc2))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
