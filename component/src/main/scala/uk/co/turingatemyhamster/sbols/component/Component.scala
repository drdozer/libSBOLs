package uk.co.turingatemyhamster.sbols.component

import uk.co.turingatemyhamster.sbols.core.{Documented, Identified}
import java.net.URI
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.validation._
import Validator._

/**
 *
 *
 * @author Matthew Pocock
 */
trait Component extends Documented {
  def componentType: URI
}

object Component {
  implicit val componentPickler: RdfEntityPickler[Component] = RdfEntityPickler.all(
    ((_: Component).componentType) picklePropertyAs Vocabulary.component.componentType_uri,
    implicitly[RdfEntityPickler[Documented]])

  implicit val componentValidator: Validator[Component] =
    (((_: Component).componentType) as "componentType" validateWith notNull) |&&|
      implicitly[Validator[Documented]]
}
