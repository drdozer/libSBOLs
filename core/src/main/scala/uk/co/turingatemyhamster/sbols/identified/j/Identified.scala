package uk.co.turingatemyhamster.sbols
package identified
package j

import java.net.URI



trait Identified {
  def getURI: URI
  def setURI(uri: URI)
}

object Identified {
  trait IdentifiedAsIdentified[I <: Identified] extends AsIdentified[I] {
      /**
       * Retrieve the identifying URI of an identified instance.
       *
       * @param i the instance
       * @return  the identifying URI of this instance
       */
      def uri(i: I) = i.getURI
    }

  implicit def identifiedAsIdentified[I <: Identified] = new IdentifiedAsIdentified[I] {}
}

class IdentifiedImpl(private var uri: URI) extends Identified {
  def this() = this(null)
  def getURI = uri
  def setURI(uri: URI) { this.uri = uri }

  override def equals(obj: Any) = {
    obj match {
      case that : Identified => this.getURI == that.getURI
      case _ => false
    }
  }
}
