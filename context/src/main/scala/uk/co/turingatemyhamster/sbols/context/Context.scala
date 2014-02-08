package uk.co.turingatemyhamster.sbols.context

import uk.co.turingatemyhamster.sbols.core.{Annotation, Documented, TopLevelEntity}
import java.net.URI
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

/**
 *
 *
 * @author Matthew Pocock
 */
case class Context(identity: URI,
                   annotations: Seq[Annotation],
                   name: Option[String],
                   description: Option[String],
                   displayId: Option[String]
                    ) extends Documented with TopLevelEntity

object Context {
  implicit def contextPickler: RdfEntityPickler[Context] = RdfEntityPickler.all(
    ofType(Vocabulary.context.type_uri),
    implicitly[RdfEntityPickler[Documented]]
  )
}

class ContextProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.context.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[Context]].safeCast[TopLevelEntity]
}