package uk.co.turingatemyhamster.sbols.core

import uk.co.turingatemyhamster.sbols.rdfPickler._


trait Documented extends Identified {
  def name: Option[String]
  def description: Option[String]
  def displayId: Option[String]
}

/**
 *
 *
 * @author Matthew Pocock
 */
object Documented {
  implicit val documentedPickler: RdfEntityPickler[Documented] = RdfEntityPickler.all(
    ((_: Documented).name)        picklePropertyAs Vocabulary.documented.name_uri,
    ((_: Documented).description) picklePropertyAs Vocabulary.documented.description_uri,
    ((_: Documented).displayId)   picklePropertyAs Vocabulary.documented.displayId_uri,
    implicitly[RdfEntityPickler[Identified]])
}