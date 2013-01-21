package uk.co.turingatemyhamster.sbols
package rdf

import java.net.URI


sealed trait ObjectExpr
case class SubjectExpr(subject: URI, predicates: Seq[PredicateExpr]) extends ObjectExpr
case class UntypedLiteral(value: String) extends ObjectExpr
case class PredicateExpr(predicate: URI, objects: Seq[ObjectExpr])
