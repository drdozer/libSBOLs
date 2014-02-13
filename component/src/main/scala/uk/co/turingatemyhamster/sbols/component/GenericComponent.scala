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
  override val uri = Vocabulary.genericComponent.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[GenericComponent]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.componentPrefix_value -> Vocabulary.componentNamespace_uri)
}
