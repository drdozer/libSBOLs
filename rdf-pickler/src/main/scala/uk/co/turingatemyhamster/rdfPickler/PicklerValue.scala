package uk.co.turingatemyhamster.rdfPickler

import com.hp.hpl.jena.rdf.model.{Model, Literal}

/**
 * Created by caroline on 13/01/14.
 */
trait PicklerValue[V] {
  def toLiteral(m: Model, v: V): Literal
}

object PicklerValue {
  implicit class Ops[V](val _pv: PicklerValue[V]) extends AnyVal {
    def comap[W](f: W => V): PicklerValue[W] = Comap(_pv, f)
  }

  private case class Comap[V, W](_pv: PicklerValue[V], f: W => V) extends PicklerValue[W] {
    override def toLiteral(m: Model, w: W) = _pv.toLiteral(m, f(w))
  }

  implicit val stringPickler: PicklerValue[String] = new PicklerValue[String] {
    def toLiteral(m: Model, v: String) = m.createLiteral(v)
  }

  implicit val intPickler: PicklerValue[Int] = stringPickler comap (_.toString)

  implicit val doublePickler: PicklerValue[Double] = stringPickler comap (_.toString)
}
