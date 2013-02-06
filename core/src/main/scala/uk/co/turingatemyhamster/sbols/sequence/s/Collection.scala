package uk.co.turingatemyhamster.sbols
package sequence
package s

import identified.s.Described
import java.net.URI

trait Collection[DC] extends Described {
  def components: Seq[DC]
}

object Collection {
  abstract class CollectionAsCollection[C <: Collection[DC], DC](implicit asDnaComponent: AsDnaComponent[DC])
    extends Described.DescribedAsDescribed[C] with AsCollection[C]
  {
    type _DC = DC
    implicit def AsDnaComponent = asDnaComponent

    final def components(c: C) = c.components
  }

  implicit def collectionAsCollection[DC](implicit asDnaComponent: AsDnaComponent[DC]) =
    new CollectionAsCollection[Collection[DC], DC] {}
}

case class CollectionImpl[DC](uri: URI,
                              displayId: String, name: Option[String], description: Option[String],
                              components: Seq[DC]) extends Collection[DC]