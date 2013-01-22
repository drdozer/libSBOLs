package uk.co.turingatemyhamster.sbols
package sequence
package s

import java.net.URI
import identified.s.Described

trait DnaComponent[+DS, +SA] extends Described {
  def sequenceType: Set[URI]
  def dnaSequence: Option[DS]
  def annotations: Set[SA]
}

object DnaComponent {
  trait Canonical extends DnaComponent[DnaSequence, SequenceAnnotation.Canonical]

  trait DnaComponentAsDnaComponent[DC <: DnaComponent[DS, SA], DS, SA]
    extends AsDnaComponent[DC, DS, SA] with Described.DescribedAsDescribed[DC]
  {
    final def sequenceType(dc: DC) = dc.sequenceType
    final def dnaSequence(dc: DC) = dc.dnaSequence
    final def annotations(dc: DC) = dc.annotations
  }

  implicit def dnaComponentAsDnaComponent[DC <: DnaComponent[DS, SA], DS, SA] =
    new DnaComponentAsDnaComponent[DC, DS, SA] {}

  final class UriBuilder {
    def uri(uri: URI) = new DisplayIdBuilder(uri)
  }

  final class DisplayIdBuilder(uri: URI) {
    def displayId(displayId: String) = new DnaSequenceBuilder(uri, displayId)
  }

  final class DnaSequenceBuilder[+DS, +SA](uri: URI, displayId: String, sequenceTypes: Set[URI] = Set()) {
    def sequenceType(sequenceType: URI) = new DnaSequenceBuilder(uri, displayId, sequenceTypes + sequenceType)
    def sequenceType(sequenceType: Set[URI]) = new DnaSequenceBuilder(uri, displayId, sequenceTypes ++ sequenceType)

    // todo: add builder ops for name, display, sequence, annotations

    @inline def build: DnaComponent[DS, SA] =
      DnaComponentImpl(uri, displayId, None, None, sequenceTypes, None, Set())
  }

  @inline implicit def build[DS, SA](dsb: DnaSequenceBuilder[DS, SA]): DnaComponent[DS, SA] = dsb.build

  def apply() = new UriBuilder
}

case class DnaComponentImpl[DS, SA](uri: URI,
                                    displayId: String, name: Option[String], description: Option[String],
                                    sequenceType: Set[URI], dnaSequence: Option[DS], annotations: Set[SA])
  extends DnaComponent[DS, SA]