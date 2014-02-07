package uk.co.turingatemyhamster.sbols.core

import java.net.URI
import uk.co.turingatemyhamster.rdfPickler._
import com.hp.hpl.jena.rdf.model.Model

/**
 *
 *
 * @author Matthew Pocock
 */
trait Identified {
  def identity: URI
  def annotations: Seq[Annotation]
}

object Identified {
  implicit val identifiedAsResource: ResourceMaker[Identified] = ResourceMaker.uriAsResource.comap(_.identity)
  
  implicit val identifiedPickler: RdfEntityPickler[Identified] = new RdfEntityPickler[Identified] {
    def pickle(m: Model, entity: Identified) = {
      val iUri = identifiedAsResource.makeResource(m, entity)
      val uriRM = implicitly[ResourceMaker[URI]]

      for(a <- entity.annotations) {
        val p = m.createProperty(a.predicate.toString)
        a.value match {
          case ReferenceValue(value) =>
            m.createStatement(iUri, p, uriRM.makeResource(m, value))
          case StringValue(value) =>
            m.createStatement(iUri, p, value)
          case DoubleValue(value) =>
            m.createStatement(iUri, p, value.toString)
          case IntegerValue(value) =>
            m.createStatement(iUri, p, value.toString)
        }
      }
    }
  }
}

case class Annotation(predicate: URI, value: AnnotationValue)

sealed trait AnnotationValue
case class ReferenceValue(value: URI) extends AnnotationValue
case class StringValue(value: String) extends AnnotationValue
case class DoubleValue(value: Double) extends AnnotationValue
case class IntegerValue(value: Int) extends AnnotationValue