package uk.co.turingatemyhamster.sbols.component

import uk.co.turingatemyhamster.sbols.core.{Annotation, TopLevelEntity}
import java.net.URI
import uk.co.turingatemyhamster.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

/**
 *
 *
 * @author Matthew Pocock
 */
case class GenericComponent(identity: URI,
                            annotations: Seq[Annotation],
                            name: Option[String],
                            description: Option[String],
                            displayId: Option[String],
                            componentType: URI
                             ) extends Component with TopLevelEntity

object GenericComponent {
  implicit val genericComponentPickler: RdfEntityPickler[GenericComponent] = RdfEntityPickler.all(
    ofType(Vocabulary.genericComponent.type_uri),
    implicitly[RdfEntityPickler[Component]]
  )
}

class GenericComponentProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.genericComponent.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[GenericComponent]].safeCast[TopLevelEntity]
}
