package uk.co.turingatemyhamster.sbols.core

import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.validation._
import Validator._


trait Documented extends Identified {
  def name: Option[String]
  def displayId: Option[String]
  def description: Option[String]
}

/**
 *
 *
 * @author Matthew Pocock
 */
object Documented {
  implicit val documentedPickler: RdfEntityPickler[Documented] = RdfEntityPickler.all(
    ((_: Documented).description) picklePropertyAs Vocabulary.documented.description_uri,
    ((_: Documented).displayId)   picklePropertyAs Vocabulary.documented.displayId_uri,
    ((_: Documented).name)        picklePropertyAs Vocabulary.documented.name_uri,
    implicitly[RdfEntityPickler[Identified]])

  implicit val documentedValidator: Validator[Documented] =
    (((_: Documented).name) as "name" validateWith (is_eq(None: Option[String]) |<>| Validator.isSome(notNull))) |&&|
      (((_: Documented).description) as "description" validateWith (is_eq(None: Option[String]) |<>| Validator.isSome(notNull))) |&&|
      (((_: Documented).displayId) as "displayId" validateWith (is_eq(None: Option[String]) |<>| Validator.isSome(notNull))) |&&|
      implicitly[Validator[Identified]]
}