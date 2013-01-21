package uk.co.turingatemyhamster.sbols
package regulation
package s

import java.net.URI
import identified.s.Identified.IdentifiedAsIdentified
import identified.s.Identified

trait Interaction[Pl] extends Identified {
  def players: Set[Pl]
  def `type`: URI
}

object Interaction {
  trait InteractionAsInteraction[I <: Interaction[Pl], Pl] extends AsInteraction[I, Pl] with IdentifiedAsIdentified[I] {
    final def players(i: I) = i.players
    final def `type`(i: I) = i.`type`
  }

  implicit def interactionAsInteraction[I <: Interaction[Pl], Pl] = new InteractionAsInteraction[I, Pl] {}
}

case class InteractionImpl[Pl](uri: URI, players: Set[Pl], `type`: URI) extends Interaction[Pl]


trait Player[P] extends Identified {
  def port: P
  def role: URI
}

object Player {
  trait PlayerAsPlayer[Pl <: Player[P], P] extends AsPlayer[Pl, P] with IdentifiedAsIdentified[Pl] {
    final def port(pl: Pl) = pl.port
    final def role(pl: Pl) = pl.role
  }

  implicit def playerAsPlayer[Pl <: Player[P], P] = new PlayerAsPlayer[Pl, P] {}
}

case class PlayerImpl[P](uri: URI, port: P, role: URI) extends Player[P]