package uk.co.turingatemyhamster.sbols
package sequence
package s

import java.net.URI
import identified.s.Described

trait DnaComponent[DS, SA] extends Described {
  def sequenceType: Set[URI]
  def dnaSequence: Option[DS]
  def annotations: Set[SA]
}

object DnaComponent {
  trait Canonical extends DnaComponent[DnaSequence, SequenceAnnotation.Canonical]

  abstract class DnaComponentAsDnaComponent[DC <: Described, DS, SA](implicit dcCast: DC <:< DnaComponent[DS, SA])
    extends Described.DescribedAsDescribed[DC] with AsDnaComponent[DC]
  {
    type _DS = DS
    type _SA = SA

    final def sequenceType(dc: DC) = dc.sequenceType
    final def dnaSequence(dc: DC) = dc.dnaSequence
    final def annotations(dc: DC) = dc.annotations
  }

  implicit def dnaComponentAsDnaComponent[DC <: Described, DS, SA](implicit dc: DC <:< DnaComponent[DS, SA]) =
    new DnaComponentAsDnaComponent[DC, DS, SA] {}


}

case class DnaComponentImpl[DS, SA](uri: URI,
                                    displayId: String, name: Option[String], description: Option[String],
                                    sequenceType: Set[URI], dnaSequence: Option[DS], annotations: Set[SA])
  extends DnaComponent[DS, SA]