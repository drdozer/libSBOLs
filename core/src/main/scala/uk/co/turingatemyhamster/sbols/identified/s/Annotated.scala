package uk.co.turingatemyhamster.sbols
package identified
package s

import rdf.PredicateExpr
import java.net.URI

trait Annotated extends Identified {
  def annotations: Set[PredicateExpr]
}

object Annotated {
  trait AnnotatedAsAnnotated[A <: Annotated]
    extends Identified.IdentifiedAsIdentified[A]
    with AsAnnotated[A]
  {
    final def annotations(a: A) = a.annotations
  }

  implicit object annotatedAsAnnotated extends AnnotatedAsAnnotated[Annotated] {}
}

case class AnnotatedImpl(uri: URI, annotations: Set[PredicateExpr]) extends Annotated