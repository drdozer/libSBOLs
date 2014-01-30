package uk.co.turingatemyhamster.rdfPickler

/**
 * Created by caroline on 13/01/14.
 */
trait PicklerValue[V] {
  def stringify(v: V): String
}

object PicklerValue {
  implicit val stringPickler: PicklerValue[String] = new PicklerValue[String] {
    def stringify(v: String) = v
  }

  implicit def valPickler[V <: AnyVal]: PicklerValue[V] = new PicklerValue[V] {
    def stringify(v: V) = v.toString
  }
}
