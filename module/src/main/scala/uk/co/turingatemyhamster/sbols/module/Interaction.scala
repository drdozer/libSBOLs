package uk.co.turingatemyhamster.sbols.module

import uk.co.turingatemyhamster.sbols.core.{Identified, Reference, Annotation, Documented}
import java.net.URI
import uk.co.turingatemyhamster.sbols.rdfPickler._

/**
 *
 *
 * @author Matthew Pocock
 */
case class Interaction(identity: URI,
                       annotations: Seq[Annotation] = Seq(),
                       name: Option[String] = None,
                       description: Option[String] = None,
                       displayId: Option[String] = None,
                       participations: Seq[Participation] = Seq()
                        ) extends Documented

object Interaction {
  implicit def interactionPickler: RdfEntityPickler[Interaction] = RdfEntityPickler.all(
    ofType(Vocabulary.interaction.type_uri),
    ((_: Interaction).participations) picklePropertyAs Vocabulary.interaction.participation_uri,
    implicitly[RdfEntityPickler[Documented]]
  )
}

case class Participation(identity: URI,
                         annotations: Seq[Annotation] = Seq(),
                         role: URI,
                         participant: Reference[Signal]
                          ) extends Identified

object Participation {
  implicit def participationPickler: RdfEntityPickler[Participation] = RdfEntityPickler.all(
  
  )
}