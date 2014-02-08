package uk.co.turingatemyhamster.sbols.core

import uk.co.turingatemyhamster.rdfPickler.RdfEntityPickler
import com.hp.hpl.jena.rdf.model.{ModelFactory, Model}
import java.util.ServiceLoader
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntityProvider
import java.io.Writer
import java.{net => jn}
import java.util.logging.Logger

/**
 *
 *
 * @author Matthew Pocock
 */
trait SbolDocument {
  def topLevelEntities: Iterable[TopLevelEntity]
}

object SbolDocument {
  private val LOG = Logger.getLogger(SbolDocument.getClass.getName)

  case class Impl(topLevelEntities: Iterable[TopLevelEntity]) extends SbolDocument

  def providers(cl: ClassLoader): Seq[TopLevelEntityProvider] = {
    LOG.info("Searching for providers")
    val sl = ServiceLoader.load(classOf[TopLevelEntityProvider], cl)
    import scala.collection.JavaConversions._
    val ps = sl.iterator.to[Seq]
    LOG.info(f"Found ${ps.size} providers")
    ps
  }


  def documentPickler(cl: ClassLoader): RdfEntityPickler[SbolDocument] = {
    val pickler = RdfEntityPickler.all(providers(cl) map (_.pickler) :_*)

    new RdfEntityPickler[SbolDocument] {
      override def pickle(m: Model, entity: SbolDocument) =
        for(tle <- entity.topLevelEntities) {
          LOG.info(f"Pickling $tle with $pickler")
          pickler.pickle(m, tle)
          LOG.info(f"Pickled")
        }
    }
  }

  def topLevelEntities(cl: ClassLoader): Seq[jn.URI] = {
    providers(cl) map (_.uri)
  }

  trait IO {
    def write(document: SbolDocument, rdfOut: Writer)
    def write(model: Model, rdfOut: Writer)
  }

  def io(cl: ClassLoader = SbolDocument.getClass.getClassLoader,
         format: String = "RDF/XML-ABBREV") = {
    val pickler = documentPickler(cl)
    val tl = topLevelEntities(cl)

    new IO {
      def write(document: SbolDocument, rdfOut: Writer) = {
        val model = ModelFactory.createDefaultModel
        model.setNsPrefix("sbol", Vocabulary.base_uri.toString)
        pickler.pickle(model, document)
        write(model, rdfOut)
      }

      def write(model: Model, rdfOut: Writer) = {
        val tlR = tl map (uri => model createResource uri.toString)

        val writer = model.getWriter(format)
        writer.setProperty("tab", "3")
        writer.setProperty("prettyTypes", tlR.to[Array])
        
        writer.write(model, rdfOut, Vocabulary.base_uri.toString)
      }
    }
  }
}