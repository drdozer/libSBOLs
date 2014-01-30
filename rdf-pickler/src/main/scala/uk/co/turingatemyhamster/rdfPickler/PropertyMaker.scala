package uk.co.turingatemyhamster.rdfPickler

import com.hp.hpl.jena.rdf.model.{Model, Property}
import java.net.URI

/**
 * Created by caroline on 13/01/14.
 */
trait PropertyMaker {
  def propertyFor(m: Model): Property
}


object PropertyMaker {
  implicit def fromURI(uri: URI): PropertyMaker = new PropertyMaker {
    def propertyFor(m: Model) = m.createProperty(uri.toString)
  }
}