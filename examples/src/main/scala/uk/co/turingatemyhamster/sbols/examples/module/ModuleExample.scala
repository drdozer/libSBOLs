package uk.co.turingatemyhamster.sbols.examples.module

import uk.co.turingatemyhamster.sbols.module.{Signal, Module}
import uk.co.turingatemyhamster.sbols.core._
import java.io.StringWriter
import uk.co.turingatemyhamster.sbols.module.Signal
import uk.co.turingatemyhamster.sbols.module.Module
import scala.Some

/**
 *
 *
 * @author Matthew Pocock
 */
object ModuleExample {
  def main(args: Array[String]) {

    val mod_1_s_tf = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#module_1_s_tf"),
      instantiated = URI("http://turingatemyhamter.co.uk/example#tf_1"))

    val mod_1 = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#module_1"),
      name = Some("Module 1"),
      description = Some("My first module"),
      displayId = Some("mod_1"),
      signals = Seq(mod_1_s_tf),
      ports = Seq(Port(
        identity = URI("http://turingatemyhamter.co.uk/example#module_1_port_tf"),
        exposes = Reference(mod_1_s_tf.identity),
        directionality = OUT
      ))
    )

    val doc = SbolDocument.Impl(Seq(mod_1))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")
  }
}
