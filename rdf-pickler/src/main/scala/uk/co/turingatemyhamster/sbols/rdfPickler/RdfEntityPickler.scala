package uk.co.turingatemyhamster.sbols.rdfPickler

import com.hp.hpl.jena.rdf.model.Model
import scala.reflect.ClassTag
import java.util.logging.Logger

/**
 * Created by caroline on 13/01/14.
 */
trait RdfEntityPickler[E] {
  def pickle(m: Model, entity: E)
}

object RdfEntityPickler {
  implicit class Ops[E](val _p: RdfEntityPickler[E]) extends AnyVal {
    def comap[F](f: F => E): RdfEntityPickler[F] = Comap(_p, f)
    def safeCast[D](implicit ctE: ClassTag[E]): RdfEntityPickler[D] = SafeCast(_p)
  }

  private case class Comap[E, F](_p: RdfEntityPickler[E], f: F => E) extends RdfEntityPickler[F] {
    def pickle(m: Model, entity: F): Unit = _p.pickle(m, f(entity))
  }

  private case class SafeCast[E, D](_p: RdfEntityPickler[E])(implicit ctE: ClassTag[E]) extends RdfEntityPickler[D] {
    override def pickle(m: Model, entity: D) = {
      ctE.runtimeClass.isInstance(entity) match {
        case true =>
          _p.pickle(m, ctE.runtimeClass.cast(entity).asInstanceOf[E])
        case false =>
      }
    }
  }

  def byProperty[E, P](prop: E => P)(pp: RdfPropertyPickler[E, P]): RdfEntityPickler[E] = new RdfEntityPickler[E] {
    def pickle(m: Model, entity: E) = pp.pickle(m, entity, prop(entity))
  }

  def all[E](ps: RdfEntityPickler[E]*): RdfEntityPickler[E] = new RdfEntityPickler[E] {
    def pickle(m: Model, entity: E) {
      for(p <- ps) p.pickle(m, entity)
    }

    override def toString = f"All($ps)"
  }

  implicit def cast[E, EE](p: RdfEntityPickler[E])(implicit ev: EE <:< E): RdfEntityPickler[EE] = p comap ev
}

trait RdfEntityCardinalityResolver[E] {
  def resolve: RdfEntityPickler[E]
}

object RdfEntityCardinalityResolver {
  implicit def resolveEntity[P](implicit p: RdfEntityPickler[P])
  : RdfEntityCardinalityResolver[P] = new RdfEntityCardinalityResolver[P] {
      def resolve = p
  }

  implicit def resolveOptionEntity[P](implicit ep: RdfEntityPickler[P])
  : RdfEntityCardinalityResolver[Option[P]] = new RdfEntityCardinalityResolver[Option[P]] {
    def resolve = new RdfEntityPickler[Option[P]] {
      override def pickle(m: Model, op: Option[P]) = op foreach (p => ep.pickle(m, p))
    }
  }

  implicit def resolveSeqEntity[P](implicit ep: RdfEntityPickler[P])
  : RdfEntityCardinalityResolver[Seq[P]] = new RdfEntityCardinalityResolver[Seq[P]] {
    def resolve = new RdfEntityPickler[Seq[P]] {
      override def pickle(m: Model, po: Seq[P]) = po foreach (p => ep.pickle(m, p))
    }
  }
}