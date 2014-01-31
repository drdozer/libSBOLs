package uk.co.turingatemyhamster.sbols.core

import uk.co.turingatemyhamster.rdfPickler.RdfEntityPickler
import com.hp.hpl.jena.rdf.model.{ModelFactory, Model}
import java.util.ServiceLoader
import uk.co.turingatemyhamster.sbols.core.spi.TopLevelEntryProvider
import java.io.Writer
import java.net.URI

/**
 *
 *
 * @author Matthew Pocock
 */
trait SbolDocument {
  def topLevelEntrys: Iterable[TopLevelEntity]
}

object SbolDocument {
  
  def providers(cl: ClassLoader): Seq[TopLevelEntryProvider] = {
    val sl = ServiceLoader.load(classOf[TopLevelEntryProvider], cl)
    import scala.collection.JavaConversions._
    sl.iterator.to[Seq]
  }


  def documentPickler(cl: ClassLoader): RdfEntityPickler[SbolDocument] = {
    val pickler = RdfEntityPickler.all(providers(cl) map (_.pickler) :_*)

    new RdfEntityPickler[SbolDocument] {
      override def pickle(m: Model, entity: SbolDocument) =
        for(tle <- entity.topLevelEntrys) pickler.pickle(m, tle)
    }
  }

  def topLevelEntities(cl: ClassLoader): Seq[URI] = {
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
        model.setNsPrefix("sbol", Vocabulary.baseUri)
        pickler.pickle(model, document)
        write(model, rdfOut)
      }

      def write(model: Model, rdfOut: Writer) = {
        val tlR = tl map (uri => model createResource uri.toString)

        val writer = model.getWriter(format)
        writer.setProperty("tab", "3")
        writer.setProperty("prettyTypes", tlR)
        
        writer.write(model, rdfOut, Vocabulary.baseUri)
      }
    }
  }
}