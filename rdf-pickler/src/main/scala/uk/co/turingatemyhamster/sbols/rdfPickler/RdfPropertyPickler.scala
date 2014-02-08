package uk.co.turingatemyhamster.sbols.rdfPickler

import com.hp.hpl.jena.rdf.model.Model
import java.net.URI

/**
 * Created by caroline on 13/01/14.
 */
trait RdfPropertyPickler[E, P] {
  def pickle(m: Model, from: E, to: P)
}


object RdfPropertyPickler {
  implicit class Ops[E, P](val _p: RdfPropertyPickler[E, P]) extends AnyVal {
    def comap[F](f: F => E): RdfPropertyPickler[F, P] = Comap(_p, f)
  }

  private case class Comap[E, P, F](_p: RdfPropertyPickler[E, P], f: F => E) extends RdfPropertyPickler[F, P] {
    def pickle(m: Model, from: F, to: P): Unit = _p.pickle(m, f(from), to)
  }


  def all[E, P](ps: RdfPropertyPickler[E, P]*): RdfPropertyPickler[E, P] = new RdfPropertyPickler[E, P] {
    def pickle(m: Model, from: E, to: P) = for(p <- ps) p.pickle(m, from, to)
  }

  implicit def aValue[E, P](pm: PropertyMaker)
                           (implicit rmE: ResourceMaker[E], pv: PicklerValue[P])
  : RdfPropertyPickler[E, P] = new RdfPropertyPickler[E, P] {
    def pickle(m: Model, from: E, to: P) = m.addStatement(
      rmE.makeResource(m, from),
      pm.propertyFor(m),
      pv.toLiteral(m, to)
    )
  }

  implicit def anObject[E, P](pm: PropertyMaker)
                             (implicit rmE: ResourceMaker[E], rmP: ResourceMaker[P])
  : RdfPropertyPickler[E, P] = new RdfPropertyPickler[E, P] {
    def pickle(m: Model, from: E, to: P) = m.addStatement(
      rmE.makeResource(m, from),
      pm.propertyFor(m),
      rmP.makeResource(m, to))
  }
}

trait RdfPropertyCardinalityResolver[E, P] {
  def resolve: RdfPropertyPickler[E, P]
}

object RdfPropertyCardinalityResolver {
  implicit def resolveProperty[E, P](pm: PropertyMaker)(implicit ep: PropertyMaker => RdfPropertyPickler[E, P])
  : RdfPropertyCardinalityResolver[E, P] = new RdfPropertyCardinalityResolver[E, P] {
    val pp = ep(pm)
    def resolve = pp
  }

  implicit def resolveOptionProperty[E, P](pm: PropertyMaker)(implicit ep: PropertyMaker => RdfPropertyPickler[E, P])
  : RdfPropertyCardinalityResolver[E, Option[P]] = new RdfPropertyCardinalityResolver[E, Option[P]] {
    val pp = ep(pm)
    def resolve = new RdfPropertyPickler[E, Option[P]] {
      override def pickle(m: Model, from: E, to: Option[P]) = to foreach (t => pp.pickle(m, from, t))
    }
  }

  implicit def resolveSeqProperty[E, P](pm: PropertyMaker)(implicit ep: PropertyMaker => RdfPropertyPickler[E, P])
  : RdfPropertyCardinalityResolver[E, Seq[P]] = new RdfPropertyCardinalityResolver[E, Seq[P]] {
    val pp = ep(pm)
    def resolve = new RdfPropertyPickler[E, Seq[P]] {
      override def pickle(m: Model, from: E, to: Seq[P]) = to foreach (t => pp.pickle(m, from, t))
    }
  }
}