package uk.co.turingatemyhamster.sbols.component

import uk.co.turingatemyhamster.sbols.core.{Documented, Identified}
import java.net.URI
import uk.co.turingatemyhamster.rdfPickler._

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
}
