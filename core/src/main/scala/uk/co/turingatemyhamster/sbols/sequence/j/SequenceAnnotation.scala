package uk.co.turingatemyhamster.sbols
package sequence
package j

import identified.j.Identified
import java.net.URI
import java.{lang => jl, util => ju}

import scala.collection.convert.WrapAsScala._

trait SequenceAnnotation[SA, DC] extends Identified {
  def getBioStart: jl.Integer
  def setBioStart(start: jl.Integer)

  def getBioEnd: jl.Integer
  def setBioEnd(end: jl.Integer)

  def getStrand: Strand
  def setStrand(strand: Strand)

  def getPrecedes: ju.Set[SA]
  def setPrecedes(precedes: ju.Set[SA])

  def getSubComponent: DC
  def setSubComponent(dc: DC)
}

object SequenceAnnotation {

  implicit def enumAsStrand(s: Strand): sequence.Strand = s match {
    case Strand.FORWARD => sequence.Strand.+
    case Strand.BACKWARD => sequence.Strand.-
  }

  implicit def strandAsEnum(s: sequence.Strand): Strand = s match {
    case sequence.Strand.+ => Strand.FORWARD
    case sequence.Strand.- => Strand.BACKWARD
  }

  trait SequenceAnnotationAsSequenceAnnotation[SA <: SequenceAnnotation[SA, DC], DC]
    extends AsSequenceAnnotation[SA, DC]
    with Identified.IdentifiedAsIdentified[SA]
  {
    final def bioStart(sa: SA) = Option(sa.getBioStart)
    final def bioEnd(sa: SA) = Option(sa.getBioEnd)
    final def strand(sa: SA) = Option(sa.getStrand) map enumAsStrand
    final def precedes(sa: SA) = sa.getPrecedes.toSet
    final def subComponent(sa: SA) = sa.getSubComponent
  }
}

case class SequenceAnnotationImpl[DC](private var uri: URI,
                                      private var bioStart: jl.Integer,
                                      private var bioEnd: jl.Integer,
                                      private var strand: Strand,
                                      private var precedes: ju.Set[SequenceAnnotationImpl[DC]],
                                      private var subComponent: DC)
  extends SequenceAnnotation[SequenceAnnotationImpl[DC], DC]
{
  def getBioStart = bioStart
  def setBioStart(start: Integer) { this.bioStart = start }

  def getBioEnd = bioEnd
  def setBioEnd(end: Integer) { this.bioEnd = end }

  def getStrand = strand
  def setStrand(strand: Strand) { this.strand = strand }

  def getPrecedes = precedes
  def setPrecedes(precedes: ju.Set[SequenceAnnotationImpl[DC]]) { this.precedes = precedes }

  def getSubComponent = subComponent
  def setSubComponent(subComponent: DC) { this.subComponent = subComponent }

  def getURI = uri
  def setURI(uri: URI) { this.uri = uri }
}