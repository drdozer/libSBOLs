package uk.co.turingatemyhamster.sbols.examples.context

import uk.co.turingatemyhamster.sbols.core.{SbolDocument, URI}
import java.io.StringWriter
import uk.co.turingatemyhamster.sbols.context.Context

/**
 *
 *
 * @author Matthew Pocock
 */
object ContextExample {
  def main(args: Array[String]) {
    val c1 = Context(
      identity = URI("http://turingatemyhamter.co.uk/example#context1"),
      name = Some("Context 1"),
      description = Some("My first context"),
      displayId = Some("c1")
    )

    val doc = SbolDocument.Impl(Seq(c1))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
