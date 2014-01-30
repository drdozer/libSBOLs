package uk.co.turingatemyhamster.rdfPickler

import com.hp.hpl.jena.rdf.model.Model

/**
 * Created by caroline on 13/01/14.
 */
trait RdfEntityPickler[E] {
  def pickle(m: Model, entity: E)
}

object RdfEntityPickler {
  implicit class Ops[E](val _p: RdfEntityPickler[E]) extends AnyVal {
    def comap[F](f: F => E): RdfEntityPickler[F] = Comap(_p, f)
  }

  private case class Comap[E, F](_p: RdfEntityPickler[E], f: F => E) extends RdfEntityPickler[F] {
    def pickle(m: Model, entity: F): Unit = _p.pickle(m, f(entity))
  }

  def byProperty[E, P](prop: E => P)(implicit pp: RdfPropertyPickler[E, P]): RdfEntityPickler[E] = new RdfEntityPickler[E] {
    def pickle(m: Model, entity: E) = pp.pickle(m, entity, prop(entity))
  }

  def all[E](ps: RdfEntityPickler[E]*): RdfEntityPickler[E] = new RdfEntityPickler[E] {
    def pickle(m: Model, entity: E) = new RdfEntityPickler[E] {
      def pickle(m: Model, entity: E) = for(p <- ps) p.pickle(m, entity)
    }
  }

}

