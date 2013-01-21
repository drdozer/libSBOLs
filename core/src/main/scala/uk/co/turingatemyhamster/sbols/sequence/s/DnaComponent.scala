package uk.co.turingatemyhamster.sbols
package sequence
package s

import java.net.URI
import identified.s.Described

trait DnaComponent[DS, SA] extends Described {
  def sequenceType: Set[URI]
  def dnaSequence: DS
  def annotations: Set[SA]
}

object DnaComponent {
  trait DnaComponentAsDnaComponent[DC <: DnaComponent[DS, SA], DS, SA]
    extends AsDnaComponent[DC, DS, SA] with Described.DescribedAsDescribed[DC]
  {
    final def sequenceType(dc: DC) = dc.sequenceType
    final def dnaSequence(dc: DC) = dc.dnaSequence
    final def annotations(dc: DC) = dc.annotations
  }

  implicit def dnaComponentAsDnaComponent[DC <: DnaComponent[DS, SA], DS, SA] =
    new DnaComponentAsDnaComponent[DC, DS, SA] {}
}

case class DnaComponentImpl[DS, SA](uri: URI,
                                    displayId: String, name: Option[String], description: Option[String],
                                    sequenceType: Set[URI], dnaSequence: DS, annotations: Set[SA])
  extends DnaComponent[DS, SA]