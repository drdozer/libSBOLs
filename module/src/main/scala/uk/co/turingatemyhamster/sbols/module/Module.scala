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
                  annotations: Seq[Annotation] = Seq(),
                  name: Option[String] = None,
                  description: Option[String] = None,
                  displayId: Option[String] = None,
                  signals: Seq[Signal] = Seq(),
                  subModules: Seq[SubModule] = Seq(),
                  interactions: Seq[Interaction] = Seq(),
                  ports: Seq[Port[Signal]] = Seq(),
                  portMaps: Seq[PortMap[Signal, Signal, Component]] = Seq(),
                  contexts: Seq[Reference[Context]] = Seq(),
                  models: Seq[Reference[Model]] = Seq()
                   ) extends Documented with TopLevelEntity

object Module {
  implicit def modulePickler: RdfEntityPickler[Module] = RdfEntityPickler.all(
    ofType(Vocabulary.module.type_uri),
    ((_: Module).models)        picklePropertyAs Vocabulary.module.model_uri,
    ((_: Module).contexts)      picklePropertyAs Vocabulary.module.context_uri,
    ((_: Module).portMaps)      picklePropertyAs Vocabulary.module.portMap_uri,
    ((_: Module).portMaps)      pickleValue,
    ((_: Module).ports)         picklePropertyAs Vocabulary.module.port_uri,
    ((_: Module).interactions)  picklePropertyAs Vocabulary.module.interaction_uri,
    ((_: Module).interactions)  pickleValue,
    ((_: Module).ports)         pickleValue,
    ((_: Module).subModules)    picklePropertyAs Vocabulary.module.subModule_uri,
    ((_: Module).subModules)    pickleValue,
    ((_: Module).signals)       picklePropertyAs Vocabulary.module.signal_uri,
    ((_: Module).signals)       pickleValue,
    implicitly[RdfEntityPickler[Documented]]
  )
}

class ModuleProvider extends TopLevelEntityProvider {
  override def uri = Vocabulary.module.type_uri
  override def pickler: RdfEntityPickler[TopLevelEntity] =
    implicitly[RdfEntityPickler[Module]].safeCast
  override def prefixes = Seq(Vocabulary.modulePrefix_value -> Vocabulary.moduleNamespace_uri)
}

case class Signal(identity: URI,
                  annotations: Seq[Annotation] = Seq(),
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
                     annotations: Seq[Annotation] = Seq(),
                     instantiated: URI) extends Identified with Instantiation[Module]

object SubModule {
  implicit def subModulePickler: RdfEntityPickler[SubModule] = RdfEntityPickler.all(
    ofType(Vocabulary.subModule.type_uri),
    implicitly[RdfEntityPickler[Instantiation[Module]]],
    implicitly[RdfEntityPickler[Identified]]
  )
}