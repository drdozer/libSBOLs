package uk.co.turingatemyhamster.sbols.module

import uk.co.turingatemyhamster.sbols.core._
import java.net.URI
import com.hp.hpl.jena.rdf.model.Model
import uk.co.turingatemyhamster.sbols.component.Component
import uk.co.turingatemyhamster.sbols.core.Annotation
import uk.co.turingatemyhamster.sbols.context.Context
import uk.co.turingatemyhamster.sbols.rdfPickler._
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider

/**
 *
 *
 * @author Matthew Pocock
 */
case class Module(identity: URI,
                  annotations: Seq[Annotation],
                  name: Option[String],
                  description: Option[String],
                  displayId: Option[String],
                  signals: Seq[Signal],
                  subModules: Seq[SubModule],
                  interactions: Seq[Interaction],
                  ports: Seq[Port[Signal]],
                  contexts: Seq[Reference[Context]],
                  models: Seq[Reference[Model]]
                   ) extends Documented with TopLevelEntity

object Module {
  implicit def modulePickler: RdfEntityPickler[Module] = RdfEntityPickler.all(
    ofType(Vocabulary.module.type_uri),
    ((_: Module).subModules)    picklePropertyAs Vocabulary.module.subModule_uri,
    ((_: Module).subModules)    pickleValue,
    ((_: Module).interactions)  picklePropertyAs Vocabulary.module.interaction_uri,
    ((_: Module).interactions)  pickleValue,
    ((_: Module).ports)         picklePropertyAs Vocabulary.module.port_uri,
    ((_: Module).ports)         pickleValue,
    ((_: Module).contexts)      picklePropertyAs Vocabulary.module.context_uri,
    ((_: Module).models)        picklePropertyAs Vocabulary.module.model_uri,
    // signals must be after ports to ensure signals are not nested within ports that refer to them
    ((_: Module).signals)       picklePropertyAs Vocabulary.module.signal_uri,
    ((_: Module).signals)       pickleValue,
    implicitly[RdfEntityPickler[Documented]]
  )
}

class ModuleProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.module.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[Module]].safeCast
}

case class Signal(identity: URI,
                  annotations: Seq[Annotation],
                  instantiated: URI
                   ) extends Identified with Instantiation[Component]

object Signal {
  implicit def signalPickler: RdfEntityPickler[Signal] = RdfEntityPickler.all(
    ofType(Vocabulary.signal.type_uri),
    implicitly[RdfEntityPickler[Instantiation[Component]]],
    implicitly[RdfEntityPickler[Identified]]
  )
}

case class SubModule(identity: URI,
                     annotations: Seq[Annotation],
                     instantiated: URI) extends Identified with Instantiation[Module]

object SubModule {
  implicit def subModulePickler: RdfEntityPickler[SubModule] = RdfEntityPickler.all(
    ofType(Vocabulary.subModule.type_uri),
    implicitly[RdfEntityPickler[Instantiation[Module]]],
    implicitly[RdfEntityPickler[Identified]]
  )
}