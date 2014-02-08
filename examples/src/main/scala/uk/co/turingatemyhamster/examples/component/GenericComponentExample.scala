package uk.co.turingatemyhamster.examples.component

import uk.co.turingatemyhamster.sbols.component._
import uk.co.turingatemyhamster.sbols.core._
import java.io.StringWriter
import scala.Some
import uk.co.turingatemyhamster.sbols.core.Annotation

/**
 *
 *
 * @author Matthew Pocock
 */
object GenericComponentExample {
  def main(args: Array[String]) {
    val gc1 = GenericComponent(
        identity = URI("http://turingatemyhamter.co.uk/example#glucose"),
        annotations = Seq(
          Annotation(
            URI("http://turingatemyhamster.co.uk/examples#chebi_id"),
            StringValue("CHEBI:15903")),
          Annotation(
            URI("http://turingatemyhamster.co.uk/examples#InChi"),
            StringValue("nChI=1S/C6H12O6/c7-1-2-3(8)4(9)5(10)6(11)12-2/h2-11H,1H2/t2-,3-,4+,5-,6-/m1/s1")
          )
        ),
        name = Some("beta-D-glucose"),
        description = None,
        displayId = Some("β-D-glucose"),
        componentType = URI("http://www.ebi.ac.uk/chebi/searchId.do?chebiId=CHEBI:15903")
    )

    val doc = SbolDocument.Impl(Seq(gc1))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
