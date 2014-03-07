package uk.co.turingatemyhamster.sbols.examples.module

import uk.co.turingatemyhamster.sbols.module._
import uk.co.turingatemyhamster.sbols.core._
import uk.co.turingatemyhamster.sbols.component.{DnaComponent, ProteinComponent}
import java.io.StringWriter
import scala.Some
import scala.Some

/**
 *
 *
 * @author Matthew Pocock
 */
object ModuleCompositionExample {
  def main(args: Array[String]) {

    val repressorProteinComp = ProteinComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#repressor_protein"),
      description = Some("Transcriptional repressor protein, mediated by catabolite repression"),
      functionalType = Seq(URI("http://purl.obolibrary.org/obo/GO_0045013")) // carbon catabolite repression of transcription
    )

    val repressorProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/repressorProteinComp"),
      instantiated = repressorProteinComp.identity
    )

    val repressedPromoterComp = DnaComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#repressed_promoter"),
      description = Some("Repressible promoter"),
      functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000167")) // promoter
    )

    val repressedPromoterComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/repressedPromoterComp"),
      instantiated = repressedPromoterComp.identity
    )

    val repressedPromoterComp_port = Port(
      identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/repressedPromoterComp_port"),
      exposes = Reference(repressedPromoterComp_sig),
      directionality = OUT
    )

    val repressorModule = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#repressor_module"),
      name = Some("Transcriptional Repressor"),
      displayId = Some("t-rep"),
      signals = Seq(repressorProteinComp_sig, repressedPromoterComp_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/repression"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/repression/repressor"),
              role = URI("http://www.ebi.ac.uk/sbo/main/SBO:0000020"),
              participant = Reference(repressorProteinComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/repression/repressor"),
              role = URI("http://www.ebi.ac.uk/sbo/main/SBO:0000598"), // SBO:Promoter
              participant = Reference(repressedPromoterComp_sig)
            )
          )
        )
      ),
      ports = Seq(repressedPromoterComp_port)
    )

    val exprPromoter = DnaComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#a_promoter"),
      description = Some("A really useful catabolite-repressed promoter"),
      functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000167")) // promoter
    )

    val exprPromoter_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#expr_c/a_promoter"),
      instantiated = exprPromoter.identity
    )

    val exprPromoter_port = Port(
      identity = URI("http://turingatemyhamter.co.uk/example#expr_c/a_promoter_port"),
      exposes = Reference(exprPromoter_sig),
      directionality = IN
    )

    val gfpCds = DnaComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#gfp_cds"),
      functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000316"))
    )

    val gfpCds_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#expr_c/gfp_cds"),
      instantiated = gfpCds.identity
    )

    val gfpProt = ProteinComponent(
      identity = URI("http://turingatemyhamter.co.uk/example#gfp_protein"),
      functionalType = Seq(URI("http://purl.obolibrary.org/obo/MI_0367"))
    )

    val gfpProt_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#expr_c/gfp_protein"),
      instantiated = gfpProt.identity
    )

    val transcriptionalModule = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#expr_c"),
      name = Some("Expression Cassette"),
      displayId = Some("expr_c"),
      signals = Seq(exprPromoter_sig, gfpCds_sig, gfpProt_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#expr_c/gfp_cds_encodes_protein"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#expr_c/gfp_cds_encodes_protein/gfp_cds"),
              role = URI("http://turingatemyhamter.co.uk/example#dna_template"),
              participant = Reference(gfpCds_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#expr_c/gfp_cds_encodes_protein/gfp_prot"),
              role = URI("http://turingatemyhamter.co.uk/example#gene_product"),
              participant = Reference(gfpProt_sig)
            )
          )
        )
      ),
      ports = Seq(exprPromoter_port)
    )

    val unifiedPromoter_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#composite/promoter"),
      instantiated = exprPromoter.identity
    )

    val compositeModule = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#composite"),
      name = Some("Composite of regulator and expression cassette"),
      subModules = Seq(
        SubModule(
          identity = URI("http://turingatemyhamter.co.uk/example#composite/represor_module"),
          instantiated = repressorModule.identity),
        SubModule(
          identity = URI("http://turingatemyhamter.co.uk/example#composite/expr_c"),
          instantiated = transcriptionalModule.identity
        )
      ),
      signals = Seq(unifiedPromoter_sig),
      portMaps = Seq(
        PortMap(
          identity = URI("http://turingatemyhamter.co.uk/example#composite/promoter:represor_module/repressed_promoter"),
          mappedPort = Reference(repressedPromoterComp_port),
          mappedTo = Reference(unifiedPromoter_sig)
        ),
        PortMap(
          identity = URI("http://turingatemyhamter.co.uk/example#composite/promoter:expr_c/a_promoter"),
          mappedPort = Reference(exprPromoter_port),
          mappedTo = Reference(unifiedPromoter_sig)
        )
      )
    )

    val doc = SbolDocument.Impl(Seq(
      compositeModule,
      exprPromoter, gfpCds, gfpProt, transcriptionalModule,
      repressorProteinComp, repressedPromoterComp, repressorModule
    ))
    println(f"The raw document is: $doc")

    val out = new StringWriter

    SbolDocument.io().write(doc, out)

    println(f"The RDF is\n${out.toString}")

  }
}
