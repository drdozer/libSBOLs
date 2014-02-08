package uk.co.turingatemyhamster.sbols.context

import java.util.Properties
import uk.co.turingatemyhamster.sbols.core.URI

/**
 *
 *
 * @author Matthew Pocock
 */
object Vocabulary extends Vocabulary({
  val props = new Properties()
  props.load(classOf[Vocabulary].getResourceAsStream("Vocabulary.properties"))
  props
})

class Vocabulary(props: Properties) {
  private def lookup(p: String) = {
    val value = props getProperty p
    assert(value != null, f"Vocabulary for `$p` not defined")
    URI(value)
  }

  object context {
    val `type` = "Context.type"
    val type_uri = lookup(`type`)
  }
}

