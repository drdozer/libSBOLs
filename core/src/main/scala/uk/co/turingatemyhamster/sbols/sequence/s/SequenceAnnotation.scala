package uk.co.turingatemyhamster.sbols
package sequence
package s

import identified.s.Identified
import java.net.URI

trait SequenceAnnotation[SA, DC] extends Identified {
  def bioStart: Option[Int]
  def bioEnd: Option[Int]
  def strand: Option[Strand]

  def precedes: Set[SA]
  def subComponent: DC
}

object SequenceAnnotation {
  trait SequenceAnnotationAsSequenceAnnotation[SA <: SequenceAnnotation[SA, DC], DC]
    extends AsSequenceAnnotation[SA, DC]
    with Identified.IdentifiedAsIdentified[SA]
  {
    final def bioStart(sa: SA) = sa.bioStart
    final def bioEnd(sa: SA) = sa.bioEnd
    final def strand(sa: SA) = sa.strand
    final def precedes(sa: SA) = sa.precedes
    final def subComponent(sa: SA) = sa.subComponent
  }
}

case class SequenceAnnotationImpl[DC](uri: URI,
                                      bioStart: Option[Int], bioEnd: Option[Int], strand: Option[Strand],
                                      precedes: Set[SequenceAnnotationImpl[DC]], subComponent: DC)
  extends SequenceAnnotation[SequenceAnnotationImpl[DC], DC]