package uk.co.turingatemyhamster.sbols
package identified

import uk.co.turingatemyhamster.sbols.rdf.{SubjectExpr, PredicateExpr}

/**
 * An entity that can be annotated with additional RDF.
 *
 * @author Matthew Pocock
 */
trait AsAnnotated[A] extends AsIdentified[A] {
  def annotations(a: A): Set[PredicateExpr]
}

object AsAnnotated {

  /**
   * Expand an annotated instance out into the implied RDF.
   *
   * @param a the annotated instance (must also be Identified)
   * @return  RDF statement trees that capture the annotations on this instance
   */
  def asRdf[A](a : A)(implicit aa: AsAnnotated[A]): Set[SubjectExpr] = aa annotations a map { pe => SubjectExpr(aa uri a, List(pe)) }

}