package uk.co.turingatemyhamster

import java.net.URI
import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.vocabulary.RDF

/**
 *
 *
 * @author Matthew Pocock
 */
package object rdfPickler {
  implicit class FunctionPickler[E, P](val _f: E => P) extends AnyVal {
    def picklePropertyAs[PM](pm: PM)
                            (implicit pmM: PM => PropertyMaker, ep: PropertyMaker => RdfPropertyCardinalityResolver[E, P])
    : RdfEntityPickler[E] =
      RdfEntityPickler.byProperty(_f)(ep(pm).resolve)

    def pickleValue[PM](implicit ep: RdfEntityCardinalityResolver[P]): RdfEntityPickler[E] = ep.resolve comap _f
  }

  def ofType[E](entityType: URI)(implicit rmE: ResourceMaker[E]): RdfEntityPickler[E] = new RdfEntityPickler[E] {
    override def pickle(m: Model, entity: E) = m.createStatement(
      rmE.makeResource(m, entity),
      RDF.`type`,
      implicitly[ResourceMaker[URI]].makeResource(m, entityType))
  }

  def sandwiches(entityType: URI) = ???
}
