package uk.co.turingatemyhamster.rdfPickler

import com.hp.hpl.jena.rdf.model.Model

/**
 * Created by caroline on 13/01/14.
 */
trait RdfPropertyPickler[E, P] {
  def pickle(m: Model, from: E, to: P)
}


object RdfPropertyPickler {
  implicit class Ops[E, P](val _p: RdfPropertyPickler[E, P]) extends AnyVal {
    def comap[F](f: F => E): RdfPropertyPickler[F, P] = new RdfPropertyPickler[F, P] {
      def pickle(m: Model, from: F, to: P) = _p.pickle(m, f(from), to)
    }
  }

  implicit def collectionPropertyPickler[E, P, CP](implicit cp: CP <:< Iterable[P], ep: RdfPropertyPickler[E, P])
  : RdfPropertyPickler[E, CP] = new RdfPropertyPickler[E, CP] {
    def pickle(m: Model, from: E, to: CP) = for(p <- to) ep.pickle(m, from, p)
  }

  def all[E, P](ps: RdfPropertyPickler[E, P]*): RdfPropertyPickler[E, P] = new RdfPropertyPickler[E, P] {
    def pickle(m: Model, from: E, to: P) = for(p <- ps) p.pickle(m, from, to)
  }

  def anObject[E, P](pm: PropertyMaker)(implicit rmE: ResourceMaker[E], rmP: ResourceMaker[P]): RdfPropertyPickler[E, P] = new RdfPropertyPickler[E, P] {
    def pickle(m: Model, from: E, to: P) = m.createStatement(
      rmE.makeResource(m, from),
      pm.propertyFor(m),
      rmP.makeResource(m, to))
  }

  def aValue[E, P](pm: PropertyMaker)(implicit rmE: ResourceMaker[E], pv: PicklerValue[P]): RdfPropertyPickler[E, P] = new RdfPropertyPickler[E, P] {
    def pickle(m: Model, from: E, to: P) = m.createStatement(
      rmE.makeResource(m, from),
      pm.propertyFor(m),
      pv.stringify(to)
    )
  }

  def walkTo[E, P](implicit pp: RdfEntityPickler[P]): RdfPropertyPickler[E, P] = new RdfPropertyPickler[E, P] {
    def pickle(m: Model, from: E, to: P) = pp.pickle(m, to)
  }
}