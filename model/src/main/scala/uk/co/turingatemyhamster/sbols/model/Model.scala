package uk.co.turingatemyhamster.sbols.model

import uk.co.turingatemyhamster.sbols.core.{Annotation, Documented, TopLevelEntity}
import java.net.URI
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider
import uk.co.turingatemyhamster.sbols.validation._
import Validator._

case class Model(identity: URI,
                 annotations: Seq[Annotation] = Seq(),
                 name: Option[String] = None,
                 description: Option[String] = None,
                 displayId: Option[String] = None,
                 source: URI,
                 language: URI,
                 framework: URI,
                 role: URI) extends Documented with TopLevelEntity

object Model {
  implicit val modelPickler: RdfEntityPickler[Model] = RdfEntityPickler.all(
    ofType(Vocabulary.model.type_uri),
    ((_: Model).role) picklePropertyAs Vocabulary.model.role_uri,
    ((_: Model).framework) picklePropertyAs Vocabulary.model.framework_uri,
    ((_: Model).language) picklePropertyAs Vocabulary.model.language_uri,
    ((_: Model).source) picklePropertyAs Vocabulary.model.source_uri,
    implicitly[RdfEntityPickler[Documented]]
  )

  implicit val modelValidator: Validator[Model] =
    (((_: Model).source) as "source" validateWith notNull) |&&|
      (((_: Model).language) as "language" validateWith notNull) |&&|
      (((_: Model).framework) as "framework" validateWith notNull) |&&|
      (((_: Model).role) as "role" validateWith notNull) |&&|
      implicitly[Validator[Documented]]
}

class ModelProvider extends TopLevelEntityProvider {
  override val uri = Vocabulary.model.type_uri
  override val pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[Model]].safeCast[TopLevelEntity]
  override val prefixes = Seq(Vocabulary.modelPrefix_value -> Vocabulary.modelNamespace_uri)
}