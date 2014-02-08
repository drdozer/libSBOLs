package uk.co.turingatemyhamster.sbols.model

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

  object model {
    val `type` = "Model.type"
    val type_uri = lookup(`type`)

    val source = "Model.source"
    val source_uri = lookup(source)

    val language = "Model.language"
    val language_uri = lookup(language)

    val framework = "Model.framework"
    val framework_uri = lookup(framework)

    val role = "Model.role"
    val role_uri = lookup(role)
  }
}