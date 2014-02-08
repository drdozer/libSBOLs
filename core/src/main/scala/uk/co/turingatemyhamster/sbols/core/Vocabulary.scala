package uk.co.turingatemyhamster.sbols.core

import java.util.Properties

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

  val base = "baseUri"
  val base_uri = lookup(base)
  
  object documented {
    val name = "Documented.name"
    val name_uri = lookup(name)

    val displayId = "Documented.displayId"
    val displayId_uri = lookup(displayId)

    val description = "Documented.description"
    val description_uri = lookup(description)
  }
}