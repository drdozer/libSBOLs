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

class Vocabulary(props: Properties) extends VocabularyBase(props) {
  val basePrefix = "basePrefix"
  val basePrefix_value = lookupRaw(basePrefix)

  val baseNamespace = "baseNamespace"
  val baseNamespace_uri = lookup(baseNamespace)
  
  object documented {
    val name = "Documented.name"
    val name_uri = lookup(name)

    val displayId = "Documented.displayId"
    val displayId_uri = lookup(displayId)

    val description = "Documented.description"
    val description_uri = lookup(description)
  }

  object port {
    val `type` = "Port.type"
    val type_uri = lookup(`type`)

    val exposes = "Port.exposes"
    val exposes_uri = lookup(exposes)

    val directionality = "Port.directionality"
    val directionality_uri = lookup(directionality)
  }

  object directionality {
    val in = "Directionality.in"
    val in_uri = lookup(in)

    val out = "Directionality.out"
    val out_uri = lookup(out)

    val in_out = "Directionality.in_out"
    val in_out_uri = lookup(in_out)
  }

  object instantiation {
    val instantiated = "Instantiation.instantiated"
    val instantiated_uri = lookup(instantiated)
  }
}