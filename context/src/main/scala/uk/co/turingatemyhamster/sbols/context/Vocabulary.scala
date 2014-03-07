package uk.co.turingatemyhamster.sbols.context

import java.util.Properties
import uk.co.turingatemyhamster.sbols.core.{VocabularyBase, URI}

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

class Vocabulary(props: Properties) extends VocabularyBase(props) {
  val contextPrefix = "contextPrefix"
  val contextPrefix_value = lookupRaw(contextPrefix)

  val contextNamespace = "contextNamespace"
  val contextNamespace_uri = lookup(contextNamespace)

  object context {
    val `type` = "Context.type"
    val type_uri = lookup(`type`)
  }
}

