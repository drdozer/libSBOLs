package uk.co.turingatemyhamster.sbols.component

import uk.co.turingatemyhamster.sbols.core.{Annotation, TopLevelEntity}
import java.net.URI
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider
import uk.co.turingatemyhamster.sbols.validation.Validator

/**
 *
 *
 * @author Matthew Pocock
 */
case class GenericComponent(identity: URI,
                            annotations: Seq[Annotation] = Seq(),
                            name: Option[String] = None,
                            description: Option[String] = None,
                            displayId: Option[String] = None,
                            componentType: URI
                             ) extends Component with TopLevelEntity

object GenericComponent {
  implicit val genericComponentPickler: RdfEntityPickler[GenericComponent] = RdfEntityPickler.all(
    ofType(Vocabulary.genericComponent.type_uri),
    implicitly[RdfEntityPickler[Component]]
  )

  implicit val genericComponentValidator: Validator[GenericComponent] =
    implicitly[Validator[Component]]
}

class GenericComponentProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.genericComponent.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[GenericComponent]].safeCast[TopLevelEntity]
  override def prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}
