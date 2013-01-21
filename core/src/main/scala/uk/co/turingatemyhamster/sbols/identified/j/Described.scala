package uk.co.turingatemyhamster.sbols
package identified
package j

import java.net.URI

trait Described extends Identified {

  def getDisplayId: String
  def setDisplayId(id: String)

  def getName: String
  def setName(name: String)

  def getDescription: String
  def setDescription(description: String)

}

object Described {
  trait DescribedAsDescribed[D <: Described] extends AsDescribed[D] with Identified.IdentifiedAsIdentified[D] {
    final def displayId(d: D) = d.getDisplayId
    final def name(d: D) = Option(d.getName)
    final def description(d: D) = Option(d.getDescription)
  }
  implicit object describedAsDescribed extends DescribedAsDescribed[Described]
}

case class DescribedImpl(private var uri: URI, private var displayId: String, private var name: String, private var description: String) extends Described {
  def getDisplayId = displayId

  def setDisplayId(displayId: String) { this.displayId = displayId }

  def getName = name

  def setName(name: String) { this.name = name }

  def getDescription = description

  def setDescription(description: String) { this.description = description }

  def getURI = uri

  def setURI(uri: URI) { this.uri = uri }
}