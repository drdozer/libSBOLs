package uk.co.turingatemyhamster.sbols

import java.net.URI

/**
 * Created with IntelliJ IDEA.
 * User: nmrp3
 * Date: 03/12/12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
package object rdf {
  implicit def asUri(s: String): URI = new URI(s)

  implicit def asSubjectExpr[U](u: U)(implicit uU: U => URI): SubjectExpr = SubjectExpr(u, Nil)
  implicit def asSubjectExpr[U](u: U, ps: PredicateExpr*)(implicit uU: U => URI): SubjectExpr = SubjectExpr(u, ps)

  implicit def asPredicateExpr[U](u: U, objs: ObjectExpr*)(implicit uU: U => URI): PredicateExpr = PredicateExpr(u, objs)

  implicit def asObjectExpr(s: String) = UntypedLiteral(s)
}
