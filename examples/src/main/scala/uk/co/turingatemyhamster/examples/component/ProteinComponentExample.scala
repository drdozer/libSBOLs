package uk.co.turingatemyhamster.examples.component

import uk.co.turingatemyhamster.sbols.component._
import uk.co.turingatemyhamster.sbols.core.{Reference, SbolDocument, URI}
import java.io.StringWriter
import scala.Some

/**
 *
 *
 * @author Matthew Pocock
 */
object ProteinComponentExample {
  def main(args: Array[String]) {
    val ps1 = ProteinSequence(
      identity = URI("http://turingatemyhamster.co.uk/example#ps1"),
      annotations = Seq(),
      name = None,
      description = None,
      displayId = None,
      primarySequence = "liavkaflbhja"
    )

    val pc1 = ProteinComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#pc1"),
        annotations = Seq(),
        name = Some("Protein Component 1"),
        description = Some("My first Protein Component"),
        displayId = Some("pc1"),
        functionalType = Seq(),
        sequence = Some(Reference(ps1.identity)),
        sequenceAnnotations = Seq()
    )

    val pc2 = ProteinComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#pc2"),
      annotations = Seq(),
      name = Some("Protein Component 2"),
      description = Some("My second Protein Component"),
      displayId = Some("rc2"),
      functionalType = Seq(),
      sequence = None,
      sequenceAnnotations = Seq(
        SequenceAnnotation.Impl(
          identity = URI("http://turingatemyhamter.co.uk/example#pc2/sa1"),
          annotations = Seq(),
          bioStart = 20,
          bioEnd = 32,
          subComponent = Reference(pc1.identity)
        )
      )
    )

    val doc = SbolDocument.Impl(Seq(ps1, pc1, pc2))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
