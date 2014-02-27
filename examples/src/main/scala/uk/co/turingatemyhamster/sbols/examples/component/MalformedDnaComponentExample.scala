package uk.co.turingatemyhamster.sbols.examples.component

import uk.co.turingatemyhamster.sbols.component.{ReverseComplement, OrientedAnnotation, DnaComponent}
import uk.co.turingatemyhamster.sbols.core.{Reference, SbolDocument, URI}
import uk.co.turingatemyhamster.sbols.validation._



/**
 *
 *
 * @author Matthew Pocock
 */
object MalformedDnaComponentExample {
  def main(args: Array[String]) {

    val dc1 = DnaComponent(identity = URI("http://turingatemyhamter.co.uk/example#dc1"))

    val dc2 = DnaComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#dc2"),
      sequenceAnnotations = Seq(
        OrientedAnnotation.Impl(
          identity = URI("http://turingatemyhamter.co.uk/example#dc2/sa1"),
          bioStart = Some(30),
          bioEnd = Some(20),
          subComponent = Reference(dc1),
          orientation = ReverseComplement
        )
      )
    )

    val doc = SbolDocument.Impl(Seq(dc1, dc2))
    println(f"The raw DC is: $dc2")

    val vd = validate(dc2)
    println(f"Validation should be an error describing bioStart and bioEnd:\n$vd")
  }
}
