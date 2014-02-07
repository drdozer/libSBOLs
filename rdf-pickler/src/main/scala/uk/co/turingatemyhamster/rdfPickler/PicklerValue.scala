package uk.co.turingatemyhamster.rdfPickler

/**
 * Created by caroline on 13/01/14.
 */
trait PicklerValue[V] {
  def stringify(v: V): String
}

object PicklerValue {
  implicit class Ops[V](val _pv: PicklerValue[V]) extends AnyVal {
    def comap[W](f: W => V): PicklerValue[W] = Comap(_pv, f)
  }

  private case class Comap[V, W](_pv: PicklerValue[V], f: W => V) extends PicklerValue[W] {
    override def stringify(w: W) = _pv.stringify(f(w))
  }

  implicit val stringPickler: PicklerValue[String] = new PicklerValue[String] {
    def stringify(v: String) = v
  }

  implicit val intPickler: PicklerValue[Int] = stringPickler comap (_.toString)
}
