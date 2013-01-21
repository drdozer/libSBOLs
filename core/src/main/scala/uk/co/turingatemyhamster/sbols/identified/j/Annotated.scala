package uk.co.turingatemyhamster.sbols
package identified
package j


import rdf.PredicateExpr

import scala.collection.convert.WrapAsScala._

import java.{util => ju}
import java.net.URI

trait Annotated extends Identified {
  def getAnnotations: ju.Set[PredicateExpr]
  def setAnnotations(annotations: ju.Set[PredicateExpr])
}


object Annotated {
  trait AnnotatedAsAnnotated[A <: Annotated] extends AsAnnotated[A] with Identified.IdentifiedAsIdentified[A] {
    final def annotations(a: A) = a.getAnnotations.toSet
  }

  implicit object annotatedAsAnnotated extends AnnotatedAsAnnotated[Annotated] {}
}

case class AnnotatedImpl(private var uri: URI, private var annotations: ju.Set[PredicateExpr]) extends Annotated {
  def getAnnotations = annotations
  def setAnnotations(annotations: ju.Set[PredicateExpr]) { this.annotations = annotations }

  def getURI = uri
  def setURI(uri: URI) { this.uri = uri }
}