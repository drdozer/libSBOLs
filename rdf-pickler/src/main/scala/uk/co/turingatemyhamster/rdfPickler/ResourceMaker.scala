package uk.co.turingatemyhamster.rdfPickler

import com.hp.hpl.jena.rdf.model.{Resource, Model}
import java.net.URI


/**
 * Created by caroline on 13/01/14.
 */
trait ResourceMaker[-R] {
  def makeResource(m: Model, r: R): Resource
}

object ResourceMaker {
  implicit class Ops[R](val _rm: ResourceMaker[R]) extends AnyVal {
    def comap[S](f: S => R): ResourceMaker[S] = Comap(_rm, f)
  }

  private case class Comap[R, S](_rm: ResourceMaker[R], f: S => R) extends ResourceMaker[S] {
    def makeResource(m: Model, r: S): Resource = _rm.makeResource(m, f(r))
  }

  implicit val uriAsResource: ResourceMaker[URI] = new ResourceMaker[URI] {
    def makeResource(m: Model, r: URI): Resource = m.createResource(r.toString)
  }
}