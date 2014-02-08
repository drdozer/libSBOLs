package uk.co.turingatemyhamster.sbols.examples.component

import uk.co.turingatemyhamster.sbols.component.{DnaSequence, ReverseComplement, OrientedAnnotation, DnaComponent}
import uk.co.turingatemyhamster.sbols.core.{Reference, SbolDocument, URI}
import java.io.StringWriter

/**
 *
 *
 * @author Matthew Pocock
 */
object DnaComponentExample {
  def main(args: Array[String]) {

    val ds1 = DnaSequence(
      identity = URI("http://turingatemyhamster.co.uk/example#ds1"),
      annotations = Seq(),
      name = None,
      description = None,
      displayId = None,
      primarySequence = "tagctagctgct"
    )

    val dc1 = DnaComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#dc1"),
        annotations = Seq(),
        name = Some("DNA Component 1"),
        description = Some("My first DNA Component"),
        displayId = Some("dc1"),
        functionalType = Seq(URI("http://purl.org/so#promoter")),
        sequence = Some(Reference(ds1.identity)),
        sequenceAnnotations = Seq()
    )

    val dc2 = DnaComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#dc2"),
      annotations = Seq(),
      name = Some("DNA Component 2"),
      description = Some("My second DNA Component"),
      displayId = Some("dc2"),
      functionalType = Seq(),
      sequence = None,
      sequenceAnnotations = Seq(
        OrientedAnnotation.Impl(
          identity = URI("http://turingatemyhamter.co.uk/example#dc2/sa1"),
          annotations = Seq(),
          bioStart = 20,
          bioEnd = 32,
          subComponent = Reference(dc1.identity),
          orientation = ReverseComplement
        )
      )
    )

    val doc = SbolDocument.Impl(Seq(ds1,dc1, dc2))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
