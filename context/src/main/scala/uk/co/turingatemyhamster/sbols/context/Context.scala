package uk.co.turingatemyhamster.sbols.context

import uk.co.turingatemyhamster.sbols.core.{Annotation, Documented, TopLevelEntity}
import java.net.URI
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider
import uk.co.turingatemyhamster.validation._
import Validator._

/**
 *
 *
 * @author Matthew Pocock
 */
case class Context(identity: URI,
                   annotations: Seq[Annotation] = Seq(),
                   name: Option[String] = None,
                   description: Option[String] = None,
                   displayId: Option[String] = None
                    ) extends Documented with TopLevelEntity

object Context {
  implicit val contextPickler: RdfEntityPickler[Context] = RdfEntityPickler.all(
    ofType(Vocabulary.context.type_uri),
    implicitly[RdfEntityPickler[Documented]]
  )

  implicit val contextValidator: Validator[Context] =
    implicitly[Validator[Documented]]
}

class ContextProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.context.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[Context]].safeCast[TopLevelEntity]
  override def prefixes = Seq(Vocabulary.contextPrefix_value -> Vocabulary.contextNamespace_uri)
}