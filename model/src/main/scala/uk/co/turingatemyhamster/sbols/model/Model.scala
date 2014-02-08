package uk.co.turingatemyhamster.sbols.model

import uk.co.turingatemyhamster.sbols.core.{Annotation, Documented, TopLevelEntity}
import java.net.URI
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

case class Model(identity: URI,
                 annotations: Seq[Annotation],
                 name: Option[String],
                 description: Option[String],
                 displayId: Option[String],
                 source: URI,
                 language: URI,
                 framework: URI,
                 role: URI) extends Documented with TopLevelEntity

object Model {
  implicit def modelPickler: RdfEntityPickler[Model] = RdfEntityPickler.all(
    ofType(Vocabulary.model.type_uri),
    ((_: Model).source) picklePropertyAs Vocabulary.model.source_uri,
    ((_: Model).language) picklePropertyAs Vocabulary.model.language_uri,
    ((_: Model).framework) picklePropertyAs Vocabulary.model.framework_uri,
    ((_: Model).role) picklePropertyAs Vocabulary.model.role_uri,
    implicitly[RdfEntityPickler[Documented]]
  )
}

class ModelProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.model.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[Model]].safeCast[TopLevelEntity]
}