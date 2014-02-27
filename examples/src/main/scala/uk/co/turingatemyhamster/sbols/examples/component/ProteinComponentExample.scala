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
object ProteinComponentExample {
  def main(args: Array[String]) {
    val ps1 = ProteinSequence(
      identity = URI("http://turingatemyhamster.co.uk/example#ps1"),
      primarySequence = "liavkaflbhja"
    )

    val pc1 = ProteinComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#pc1"),
        name = Some("Protein Component 1"),
        description = Some("My first Protein Component"),
        displayId = Some("pc1"),
        sequence = Some(Reference(ps1.identity))
    )

    val pc2 = ProteinComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#pc2"),
      name = Some("Protein Component 2"),
      description = Some("My second Protein Component"),
      displayId = Some("rc2"),
      sequenceAnnotations = Seq(
        SequenceAnnotation.Impl(
          identity = URI("http://turingatemyhamter.co.uk/example#pc2/sa1"),
          bioStart = Some(20),
          bioEnd = Some(32),
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
