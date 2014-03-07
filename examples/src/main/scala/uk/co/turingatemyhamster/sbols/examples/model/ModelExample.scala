package uk.co.turingatemyhamster.sbols.examples.model

import uk.co.turingatemyhamster.sbols.model.Model
import uk.co.turingatemyhamster.sbols.core.{SbolDocument, URI}
import java.io.StringWriter

/**
 *
 *
 * @author Matthew Pocock
 */
object ModelExample {
  def main(args: Array[String]) {
    val m1 = Model(
      identity = URI("http://turingatemyhamter.co.uk/example#model1"),
      name = Some("Model 1"),
      description = Some("My first model, intended for model composition"),
      displayId = Some("m1"),
      source = URI("http://turingatemyhamster.co.uk/example/model1.sbml"),
      language = URI("http://turingatemyhamster.co.uk/example#sbml"),
      framework = URI("http://www.ebi.ac.uk/sbo/main/SBO:0000062"),
      role = URI("http://turingatemyhamster.co.uk/example#composition")
    )

    val doc = SbolDocument.Impl(Seq(m1))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
