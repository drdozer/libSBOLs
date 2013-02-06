package uk.co.turingatemyhamster.sbols
package sequence
package j

import java.{util => ju}
import identified.j.Described
import java.net.URI
import collection.convert.WrapAsScala._

trait Collection[DC] extends Described {
  def getComponents: ju.List[DC]
  def setComponents(components: ju.List[DC])
}

object Collection {
  abstract class CollectionAsCollection[C <: Collection[DC], DC](implicit asDnaComponent: AsDnaComponent[DC])
    extends Described.DescribedAsDescribed[C] with AsCollection[C]
  {
    type _DC = DC
    implicit def AsDnaComponent = asDnaComponent

    final def components(c: C) = c.getComponents
  }

  implicit def collectionAsCollection[DC](implicit asDnaComponent: AsDnaComponent[DC]) =
    new CollectionAsCollection[Collection[DC], DC] {}
}

class CollectionImpl[DC](private var uri: URI,
                         private var displayId: String, private var name: String, private var description: String,
                         private var components: ju.List[DC]) extends Collection[DC] with Described {
  def getComponents = components
  def setComponents(components: ju.List[DC]) { this.components = components }

  def getDisplayId = displayId
  def setDisplayId(id: String) { this.displayId = id }

  def getName = name
  def setName(name: String) { this.name = name }

  def getDescription = description
  def setDescription(description: String) { this.description = description }

  def getURI = uri
  def setURI(uri: URI) { this.uri = uri }
}