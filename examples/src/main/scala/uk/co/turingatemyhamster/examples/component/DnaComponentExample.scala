package uk.co.turingatemyhamster.examples.component

import uk.co.turingatemyhamster.sbols.component.DnaComponent
import uk.co.turingatemyhamster.sbols.core.{SbolDocument, URI}
import java.io.StringWriter

/**
 *
 *
 * @author Matthew Pocock
 */
object DnaComponentExample {
  def main(args: Array[String]) {
    val dc = DnaComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#dc1"),
        annotations = Seq(),
        name = Some("DNA Component 1"),
        description = Some("My first DNA Component"),
        displayId = Some("dc1"),
        functionalType = Seq(URI("http://purl.org/so#promoter")),
        sequence = None,
        sequenceAnnotations = Seq()
    )

    println(f"The raw dc object is: $dc")

    val out = new StringWriter

    SbolDocument.io().write(SbolDocument.Impl(Seq(dc)), out)

    println(f"The RDF is\n${out.toString}")
  }
}
