package uk.co.turingatemyhamster.sbols
package sequence
package s

import identified.s.Described
import java.net.URI

trait Collection[DC] extends Described {
  def components: Seq[DC]
}

object Collection {
  trait CollectionAsCollection[C <: Collection[DC], DC] extends AsCollection[C, DC] with Described.DescribedAsDescribed[C] {
    def components(c: C) = c.components
  }

  implicit def collectionAsCollection[DC] = new CollectionAsCollection[Collection[DC], DC] {}
}

case class CollectionImpl[DC](uri: URI,
                              displayId: String, name: Option[String], description: Option[String],
                              components: Seq[DC]) extends Collection[DC]