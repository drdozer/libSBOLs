package uk.co.turingatemyhamster.sbols.core

import java.util.Properties
import java.net.URI

/**
 *
 *
 * @author Matthew Pocock
 */
object Vocabulary extends Vocabulary({
  val props = new Properties()
  props.load(classOf[Vocabulary].getClassLoader.getResourceAsStream(
    f"${classOf[Vocabulary].getCanonicalName}.properties"))
  props
})

class Vocabulary(props: Properties) {
  val base = "baseUri"
  val baseUri = props getProperty base


}