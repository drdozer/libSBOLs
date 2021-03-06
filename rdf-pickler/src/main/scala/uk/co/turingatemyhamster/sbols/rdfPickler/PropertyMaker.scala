package uk.co.turingatemyhamster.sbols.rdfPickler

import com.hp.hpl.jena.rdf.model.{Model, Property}
import java.net.URI

/**
 * Created by caroline on 13/01/14.
 */
trait PropertyMaker {
  def propertyFor(m: Model): Property
}


object PropertyMaker {
  implicit def fromURI(uri: URI): PropertyMaker = fromString(uri.toString)

  implicit def fromString(uriString: String): PropertyMaker = new PropertyMaker {
    def propertyFor(m: Model) = m.createProperty(uriString)
  }
}