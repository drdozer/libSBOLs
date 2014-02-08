package uk.co.turingatemyhamster.examples.component

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
      annotations = Seq(),
      name = None,
      description = None,
      displayId = None,
      primarySequence = "uagcuagcugcu"
    )

    val rc1 = RnaComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#rc1"),
        annotations = Seq(),
        name = Some("RNA Component 1"),
        description = Some("My first RNA Component"),
        displayId = Some("rc1"),
        functionalType = Seq(),
        sequence = Some(Reference(rs1.identity)),
        sequenceAnnotations = Seq()
    )

    val rc2 = RnaComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#rc2"),
      annotations = Seq(),
      name = Some("RNA Component 2"),
      description = Some("My second RNA Component"),
      displayId = Some("rc2"),
      functionalType = Seq(),
      sequence = None,
      sequenceAnnotations = Seq(
        OrientedAnnotation.Impl(
          identity = URI("http://turingatemyhamter.co.uk/example#rc2/sa1"),
          annotations = Seq(),
          bioStart = 20,
          bioEnd = 32,
          subComponent = Reference(rc1.identity),
          orientation = ReverseComplement
        )
      )
    )

    val doc = SbolDocument.Impl(Seq(rc1, rc2))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
