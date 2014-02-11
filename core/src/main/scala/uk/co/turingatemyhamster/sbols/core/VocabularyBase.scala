package uk.co.turingatemyhamster.sbols.core

import java.util.Properties

/**
 *
 *
 * @author Matthew Pocock
 */
class VocabularyBase(props: Properties) {
  protected def lookup(p: String) = URI(lookupRaw(p))

  protected def lookupRaw(p: String) = {
    val value = props getProperty p
    assert(value != null, f"Vocabulary for `$p` not defined")
    value
  }
}
