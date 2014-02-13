package uk.co.turingatemyhamster.sbols.core

import java.{net => jn}
import uk.co.turingatemyhamster.sbols.rdfPickler._
import com.hp.hpl.jena.rdf.model.Model
import uk.co.turingatemyhamster.sbols.validation._
import Validator._

/**
 *
 *
 * @author Matthew Pocock
 */
trait Identified {
  def identity: jn.URI
  def annotations: Seq[Annotation]
}

object Identified {
  implicit val identifiedAsResource: ResourceMaker[Identified] = ResourceMaker.uriAsResource.comap(_.identity)
  
  implicit val identifiedPickler: RdfEntityPickler[Identified] = new RdfEntityPickler[Identified] {
    def pickle(m: Model, entity: Identified) = {
      val iUri = identifiedAsResource.makeResource(m, entity)
      val uriRM = implicitly[ResourceMaker[jn.URI]]

      for(a <- entity.annotations) {
        val p = m.createProperty(a.predicate.toString)
        a.value match {
          case ReferenceValue(value) =>
            m.addStatement(iUri, p, uriRM.makeResource(m, value))
          case StringValue(value) =>
            m.addStatement(iUri, p, implicitly[PicklerValue[String]].toLiteral(m, value))
          case DoubleValue(value) =>
            m.addStatement(iUri, p, implicitly[PicklerValue[Double]].toLiteral(m, value))
          case IntegerValue(value) =>
            m.addStatement(iUri, p, implicitly[PicklerValue[Int]].toLiteral(m, value))
        }
      }
    }
  }

  implicit val identifiedValidator: Validator[Identified] =
    (((_: Identified).identity) as "identity" validateWith notNull) |&&|
      (((_: Identified).annotations) as "annotations" validateWith
        (notNull |&&| each(notNull |&&| implicitly[Validator[Annotation]])))

}

case class Annotation(predicate: jn.URI, value: AnnotationValue)

object Annotation {
  implicit val annotationValidator: Validator[Annotation] =
    (((_: Annotation).predicate) as "predicate" validateWith notNull) |&&|
      (((_: Annotation).value) as "value" validateWith
        (notNull |&&| implicitly[Validator[AnnotationValue]]))
}

sealed trait AnnotationValue
object AnnotationValue {
  implicit val annotationValueValidator: Validator[AnnotationValue] = Validator {
    (av: AnnotationValue) => av match {
      case rv : ReferenceValue  => implicitly[Validator[ReferenceValue]].validate(rv)
      case sv : StringValue     => implicitly[Validator[StringValue   ]].validate(sv)
      case dv : DoubleValue     => implicitly[Validator[DoubleValue   ]].validate(dv)
      case iv : IntegerValue    => implicitly[Validator[IntegerValue  ]].validate(iv)
    }
  }
}

case class ReferenceValue(value: jn.URI) extends AnnotationValue
object ReferenceValue {
  implicit def referenceValueValidator: Validator[ReferenceValue] =
    ((_: ReferenceValue).value) as "value" validateWith notNull
}

case class StringValue(value: String) extends AnnotationValue
object StringValue {
  implicit val stringValueValidator: Validator[StringValue] =
    ((_: StringValue).value) as "value" validateWith notNull
}

case class DoubleValue(value: Double) extends AnnotationValue
object DoubleValue {
  implicit val doubleValueValidator: Validator[DoubleValue] =
    ((_: DoubleValue).value) as "value" validateWith notNull
}

case class IntegerValue(value: Int) extends AnnotationValue
object IntegerValue {
  implicit val integerValueValidator: Validator[IntegerValue] =
    ((_: IntegerValue).value) as "value" validateWith notNull
}
