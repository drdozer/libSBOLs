package uk.co.turingatemyhamster.sbols
package visual

import java.net.URI

import scalafx.scene._
import paint.Color
import shape.{Circle, SVGPath}
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory
import com.sun.org.apache.xml.internal.resolver.tools.CatalogResolver
import scala. { xml => sxml }
import scala.xml.factory.XMLLoader
import scala.xml._
import parsing.{NoBindingFactoryAdapter, FactoryAdapter}
import scalafx.scene.Group
import scalafx.scene.Node
import com.sun.javafx.css.parser.CSSParser

/**
 * SBOL Visual code.
 *
 * @author Matthew Pocock
 */
object Glyph {

  lazy val saxParserFactory = SAXParserFactory.newInstance()
  lazy val catalogResolver  = new CatalogResolver()

  def loader = {
    new XMLLoader[Elem] {
      override val parser: SAXParser = saxParserFactory.newSAXParser()
      override val adapter: FactoryAdapter = new NoBindingFactoryAdapter() {
        override def resolveEntity(publicId: String, systemId: String) = catalogResolver.resolveEntity(publicId, systemId)
      }
    }
  }

  def svgResourceAsXml(filePrefix: String): sxml.Node = resourceAsXml("SVG/" + filePrefix + ".svg")

  def resourceAsXml(resourceName: String) = loader.load(resourceAsStream(resourceName))

  def resourceAsStream(resourceName: String): InputStream = {
    val ras = classOf[Glyph].getResourceAsStream(resourceName)
    if(ras == null) throw new NullPointerException("No resource found for " + resourceName)
    ras
  }

  val visualUriBase = new URI("http://sbols.org/visual/v1.2#")

  /*protected[visual]*/ def mkVisualUri(fragment: String) = URI.create(visualUriBase.toString + fragment)

  lazy val glyphs: Seq[Glyph] = Seq(PrimerBindingSite, RibonucleaseSite, RnaStabilityElement, Terminator, Signature,
    FivePrimeOverhang, TranslationalStartSite, ProteaseSite, Promoter, ThreePrimeOverhang, Operator,
    RestrictionEnzymeRecognitionSite, AssemblyJunction, OriginOfReplication, Insulator, ProteinStabilityElement, Cds)

  def glyphFor(g: Glyph): Node = new Group {
    children = {
      val svg = svgResourceAsXml(g.filePrefix)

      val style = svg \ "defs" \ "style" map (_.text) mkString "\n"
      val styleSheet = CSSParser.getInstance parse style

//      for(r <- styleSheet.getRules)
//        println("Rule: " + r)

      val paths = for(p <- svg \ "path";
          d <- p textAttribute "d") yield new SVGPath {
        content = d
        stroke = Color.BLACK
        delegate.setFill(null)
      }
      val circles = for(c <- svg \ "circle";
        cx <- c textAttribute "cx";
        cy <- c textAttribute "cy";
        r <- c textAttribute "r") yield new Circle {
        centerX = cx.toDouble
        centerY = cy.toDouble
        radius = r.toDouble
        stroke = Color.BLACK
        delegate.setFill(null)
      }

      paths ++ circles
    }
  }

  implicit class NodeWithAttribute(n: sxml.Node) {
    def textAttribute(name: String): Option[String] = n.attribute(name) map (_.text)
  }
}


sealed class Glyph(val name: String, val filePrefix: String, val uri: URI) {
  def this(name: String, uri: URI) = this(name, name.replaceAll(" ", "-").toLowerCase, uri)
  def this(name: String, filePrefix: String, uri: String) = this(name, filePrefix, Glyph.mkVisualUri(uri))
  def this(name: String, uri: String) = this(name, Glyph.mkVisualUri(uri))
  def this(name: String) = this(name, name)
}

case object PrimerBindingSite extends Glyph("primer binding site", "primerBindingSite")
case object RibonucleaseSite extends Glyph("ribonuclease site", "ribonucleaseSite")
case object RnaStabilityElement extends Glyph("RNA stability element", "rnaStabilityElement")
case object Terminator extends Glyph("terminator")
case object Signature extends Glyph("signature")
case object FivePrimeOverhang extends Glyph("5' overhang", "five-prime-overhang", "fivePrimeOverhang")
case object TranslationalStartSite extends Glyph("translational start site", "translationalStartSite")
case object ProteaseSite extends Glyph("protease site", "proteaseSite")
case object Promoter extends Glyph("promoter")
case object ThreePrimeOverhang extends Glyph("3' overhang", "three-prime-overhang", "threePrimeOverhang")
case object Operator extends Glyph("operator")
case object RestrictionEnzymeRecognitionSite extends Glyph("restriction enzyme recognition site", "restrictionEnzymeRecognitionSite")
case object RestrictionSiteWithNoOverhang extends Glyph("restriction site with no overhang", "restrictionSiteWithNoOverhang")
case object AssemblyJunction extends Glyph("assembly junction", "assemblyJunction")
case object OriginOfReplication extends Glyph("origin of replication", "originOfReplication")
case object Insulator extends Glyph("insulator")
case object ProteinStabilityElement extends Glyph("protein stability element", "proteinStabilityElement")
case object Cds extends Glyph("CDS", "cds")