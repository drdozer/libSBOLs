package uk.co.turingatemyhamster

import java.net.URI
import com.hp.hpl.jena.rdf.model._
import com.hp.hpl.jena.vocabulary.RDF
import java.util.logging.Logger

/**
 *
 *
 * @author Matthew Pocock
 */
package object rdfPickler {
  private val LOG = Logger.getLogger("BOB")

  implicit class ModelOps(val _m: Model) extends AnyVal {
    def addStatement(s: Resource, p: Property, o: RDFNode) {
      LOG.info(f"Adding statement <$s $p $o>")
      _m.add(_m.createStatement(s, p, o))
    }
  }

  implicit class FunctionPickler[E, P](val _f: E => P) extends AnyVal {
    def picklePropertyAs[PM](pm: PM)
                            (implicit pmM: PM => PropertyMaker, ep: PropertyMaker => RdfPropertyCardinalityResolver[E, P])
    : RdfEntityPickler[E] =
      RdfEntityPickler.byProperty(_f)(ep(pm).resolve)

    def pickleValue[PM](implicit ep: RdfEntityCardinalityResolver[P]): RdfEntityPickler[E] = ep.resolve comap _f
  }

  def ofType[E](entityType: URI)(implicit rmE: ResourceMaker[E]): RdfEntityPickler[E] = new RdfEntityPickler[E] {
    override def pickle(m: Model, entity: E) = m.addStatement(
      rmE.makeResource(m, entity),
      RDF.`type`,
      implicitly[ResourceMaker[URI]].makeResource(m, entityType))
  }
}
