package uk.co.turingatemyhamster.sbols
package sequence
package j

import java.{util => ju}
import java.net.URI
import identified.j.Described

import scala.collection.convert.WrapAsScala._

trait DnaComponent[DS, SA] extends Described {
  def getSequenceType: ju.Set[URI]
  def setSequenceType(types: ju.Set[URI])

  def getDnaSequence: DS
  def setDnaSequence(ds: DS)

  def getAnnotations: ju.Set[SA]
  def setAnnotations(sa: ju.Set[SA])
}

object DnaComponent {
  trait DnaComponentAsDnaComponent[DC <: DnaComponent[DS, SA], DS, SA]
    extends AsDnaComponent[DC, DS, SA] with Described.DescribedAsDescribed[DC]
  {
    final def sequenceType(dc: DC) = dc.getSequenceType.toSet // force an immutable copy
    final def dnaSequence(dc: DC) = dc.getDnaSequence
    final def annotations(dc: DC) = dc.getAnnotations.toSet // force an immutable copy
  }

  implicit def dnaComponentAsDnaComponent[DC <: DnaComponent[DS, SA], DS, SA] =
    new DnaComponentAsDnaComponent[DC, DS, SA] {}
}

case class DnaComponentImpl[DS, SA](private var uri: URI,
                                    private var displayId: String,
                                    private var name: String,
                                    private var description: String,
                                    private var sequenceType: ju.Set[URI],
                                    private var dnaSequence: DS,
                                    private var annotations: ju.Set[SA])
  extends DnaComponent[DS, SA]
{
  def getSequenceType = sequenceType
  def setSequenceType(types: ju.Set[URI]) { this.sequenceType = types }
  
  def getDnaSequence = dnaSequence
  def setDnaSequence(ds: DS) { this.dnaSequence = ds }

  def getAnnotations = annotations
  def setAnnotations(sa: ju.Set[SA]) { this.annotations = sa }

  def getDisplayId = displayId
  def setDisplayId(id: String) { this.displayId = id }

  def getName = name
  def setName(name: String) { this.name = name }

  def getDescription = description
  def setDescription(description: String) { this.description = description }

  def getURI = uri
  def setURI(uri: URI) { this.uri = uri }
}