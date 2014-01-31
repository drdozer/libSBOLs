package uk.co.turingatemyhamster.sbols.core

import java.net.URI

/**
 *
 *
 * @author Matthew Pocock
 */
trait Identified {
  def identity: URI
}

trait Documented {
  this : Identified =>

  def name: Option[String]
  def description: Option[String]
  def displayId: Option[String]
}

trait Annotated {
  this : Identified =>

  def annotations: Seq[Annotation]
}

case class Annotation(predicate: URI, value: AnnotationValue)

sealed trait AnnotationValue
case class ReferenceValue(value: URI) extends AnnotationValue
case class StringValue(value: String) extends AnnotationValue
case class DoubleValue(value: Double) extends AnnotationValue
case class IntegerValue(value: Int) extends AnnotationValue