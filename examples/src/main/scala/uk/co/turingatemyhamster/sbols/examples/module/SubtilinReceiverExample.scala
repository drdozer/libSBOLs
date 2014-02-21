package uk.co.turingatemyhamster.sbols.examples.module

import uk.co.turingatemyhamster.sbols.module._
import uk.co.turingatemyhamster.sbols.core._
import uk.co.turingatemyhamster.sbols.component._
import scala.Some
import uk.co.turingatemyhamster.sbols.examples.module.ProteinEncodingCdsModule
import scala.Some

/**
 *
 *
 * @author Matthew Pocock
 */
object SubtilinReceiverExample {

  val spaKProteinComp = ProteinComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#SpaK_protein"),
    description = Some("Two-component histidine kinase"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/GO_0000155")) // kinase activity
  )

  val spaKCDSComp = DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#SpaK_CDS"),
    description = Some("Two-component histidine kinase encoding CDS"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000316")) // CDS
  )

  val spaRProteinComp = ProteinComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#SpaR_protein"),
    description = Some("Two-component histidine kinase"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/GO_0000156")) // response regulator activity
  )

  val spaRCDSComp = DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#SpaR_CDS"),
    description = Some("Two-component response regulator encoding CDS"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000316")) // CDS
  )

  val gfpProteinComp = ProteinComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#GFP_protein"),
    description = Some("GFP protein"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/MI_0367")) // GFP
  )

  val gfpCDSComp = DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#GFP_CDS"),
    description = Some("GFP encoding CDS"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000316")) // CDS
  )

  val pspaSPromoterComp = DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#pspaS_promoter"),
    name = Some("pspaS promoter"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000167")) // Promoter
  )

  val pspaRKPromoterComp = DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#pspaRK_promoter"),
    name = Some("pspaRK promoter"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000167")) // Promoter
  )

  val subtilinComp = GenericComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#subtilin"),
    name = Some("subtilin"),
    componentType = URI ("http://purl.obolibrary.org/obo/CHEBI_71644")//Lantibiotic
  )

  val spaK_RBS_Comp= DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#spaK_RBS_Comp"),
    name = Some("spaK RBS"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000552")) // Promoter
  )

  val spaR_RBS_Comp= DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#spaR_RBS_Comp"),
    name = Some("spaR RBS"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000552")) // Promoter
  )

  val gfp_RBS_Comp= DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#gfp_RBS_Comp"),
    name = Some("gfp RBS"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000552")) // Promoter
  )

  val spaRKTerminatorComp= DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#spaRKTerminatorComp"),
    name = Some("spaRK Terminator"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000614")) // Promoter
  )

  val gfpTerminatorComp= DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#gfpTerminatorComp"),
    name = Some("gfp Terminator"),
    functionalType = Seq(URI("http://purl.obolibrary.org/obo/SO_0000614")) // Promoter
  )


  val spaRKOperon=DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#spaRKOperon"),
    name = Some("spaRK Operon"),
    sequenceAnnotations = Seq(
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/pspaRK"),
        bioStart = 1,
        bioEnd = 2,
        subComponent = Reference(pspaRKPromoterComp),
        orientation = Inline
      ),
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/spaK_rbs"),
        bioStart = 3,
        bioEnd = 4,
        subComponent = Reference(spaK_RBS_Comp),
        orientation = Inline
      ),
        OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/spaK"),
        bioStart = 5,
        bioEnd = 6,
        subComponent = Reference(spaKCDSComp),
        orientation = Inline
      ),
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/spaR_rbs"),
        bioStart = 7,
        bioEnd = 8,
        subComponent = Reference(spaR_RBS_Comp),
        orientation = Inline
      ),
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/spaR"),
        bioStart = 9,
        bioEnd = 10,
        subComponent = Reference(spaRCDSComp),
        orientation = Inline
      ),
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/spaRK_Terminator"),
        bioStart = 11,
        bioEnd = 12,
        subComponent = Reference(spaRKTerminatorComp),
        orientation = Inline
      )
    )
  )


  val spaSOperon=DnaComponent(
    identity = URI("http://turingatemyhamter.co.uk/example#spaSOperon"),
    name = Some("spaS Operon"),
    sequenceAnnotations = Seq(
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/pspaS"),
        bioStart = 1,
        bioEnd = 2,
        subComponent = Reference(pspaSPromoterComp),
        orientation = Inline
      ),
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/gfp_rbs"),
        bioStart = 3,
        bioEnd = 4,
        subComponent = Reference(gfp_RBS_Comp),
        orientation = Inline
      ),
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/gfp"),
        bioStart = 5,
        bioEnd = 6,
        subComponent = Reference(gfpCDSComp),
        orientation = Inline
      ),
      OrientedAnnotation.Impl(
        identity=URI("http://turingatemyhamter.co.uk/example#spaRKOperon/gfp_terminator"),
        bioStart = 7,
        bioEnd = 8,
        subComponent = Reference(gfpTerminatorComp),
        orientation = Inline
      )
    )
  )


  def spaKTranslationModule ={

    val spaKProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaK_module/SpaK_protein"),
      instantiated = spaKProteinComp.identity
    )

    val spaKProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaK_module/spaKProtein_port"),
      exposes = Reference(spaKProteinComp_sig),
      directionality = OUT
    )

    val spaKCDSComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaK_module/SpaK_CDS"),
      instantiated = spaKCDSComp.identity
    )
    val spaKCDSComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaK_module/spaKCDS_port"),
      exposes = Reference(spaKCDSComp_sig),
      directionality = OUT
    )

    val spaKTranslation_module = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaK_module"),
      name = Some("SpaK module"),
      signals = Seq(spaKProteinComp_sig, spaKCDSComp_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#SpaK_module/protein_encoding"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/protein_encoding/cds"),
              role = URI("http://purl.obolibrary.org/obo/SO_0000316"),
              participant = Reference(spaKCDSComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/protein_encoding/gene_product"),
              role = URI("http://www.ebi.ac.uk/sbo/main/SBO:0000011"), // SBO:Product
              participant = Reference(spaKProteinComp_sig)
            )
          )
        )
      ),
      ports = Seq(spaKProteinComp_port,spaKCDSComp_port)
    )

    ProteinEncodingCdsModule(spaKTranslation_module,spaKCDSComp_port,spaKProteinComp_port)
  }


  def spaRTranslationModule ={


    val spaRProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaR_module/SpaR_protein"),
      instantiated = spaRProteinComp.identity
    )

    val spaRProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaR_module/spaRProtein_port"),
      exposes = Reference(spaRProteinComp_sig),
      directionality = OUT
    )

    val spaRCDSComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaR_module/SpaR_CDS"),
      instantiated = spaRCDSComp.identity
    )
    val spaRCDSComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaR_module/spaRCDS_port"),
      exposes = Reference(spaRCDSComp_sig),
      directionality = OUT
    )

    val spaRTranslation_module = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#SpaR_module"),
      name = Some("SpaR module"),
      signals = Seq(spaRProteinComp_sig, spaRCDSComp_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#SpaR_module/protein_encoding"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/protein_encoding/cds"),
              role = URI("http://purl.obolibrary.org/obo/SO_0000316"),
              participant = Reference(spaRCDSComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/protein_encoding/gene_product"),
              role = URI("http://www.ebi.ac.uk/sbo/main/SBO:0000011"), // SBO:Product
              participant = Reference(spaRProteinComp_sig)
            )
          )
        )
      ),
      ports = Seq(spaRProteinComp_port,spaRCDSComp_port)
    )

    ProteinEncodingCdsModule(spaRTranslation_module,spaRCDSComp_port,spaRProteinComp_port)
  }


  def gfpTranslationModule ={

    val gfpProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_module/GFP_protein"),
      instantiated = gfpProteinComp.identity
    )

    val gfpProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_module/gfpProtein_port"),
      exposes = Reference(gfpProteinComp_sig),
      directionality = OUT
    )

    val gfpCDSComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_module/GFP_CDS"),
      instantiated = gfpCDSComp.identity
    )
    val gfpCDSComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_module/gfpCDS_port"),
      exposes = Reference(gfpCDSComp_sig),
      directionality = OUT
    )

    val gfpTranslation_module = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_module"),
      name = Some("GFP module"),
      signals = Seq(gfpProteinComp_sig, gfpCDSComp_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#GFP_module/protein_encoding"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/protein_encoding/cds"),
              role = URI("http://purl.obolibrary.org/obo/SO_0000316"),
              participant = Reference(gfpCDSComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#repressor_module/protein_encoding/gene_product"),
              role = URI("http://www.ebi.ac.uk/sbo/main/SBO:0000011"), // SBO:Product
              participant = Reference(gfpProteinComp_sig)
            )
          )
        )
      ),
      ports = Seq(gfpProteinComp_port,gfpCDSComp_port)
    )

    ProteinEncodingCdsModule(gfpTranslation_module,gfpCDSComp_port,gfpProteinComp_port)

  }


  def gfpProductionModule ={

    val pspaSPromoterComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/pspaS_promoter"),
      instantiated = pspaSPromoterComp.identity
    )

    val pspaSPromoterComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/pspaS_promoter_port"),
      exposes = Reference(pspaSPromoterComp_sig),
      directionality = IN
    )

    val gfp=gfpTranslationModule

    val gfpCDS_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/gfp_cds"),
      instantiated = URI("http://turingatemyhamter.co.uk/example#GFP_CDS")
    )



    val gfpProduction_module = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module"),
      name = Some("GFP production module"),
      signals = Seq(pspaSPromoterComp_sig, gfpCDS_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/transcription"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/transcription/promoter"),
              role = URI("http://purl.obolibrary.org/obo/SO_0000167"),
              participant = Reference(pspaSPromoterComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/transcription/cds"),
              role = URI("http://purl.obolibrary.org/obo/SO_0000316"), // SBO:Product
              participant = Reference(gfpCDS_sig)
            )
          )
        )
      ),
      ports = Seq(pspaSPromoterComp_port),
      subModules = Seq(
        SubModule(
        identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/gfp_translation_module"),
        instantiated = gfp.m.identity)
      ),
      portMaps = Seq(
        PortMap(
          identity = URI("http://turingatemyhamter.co.uk/example#GFP_production_module/transcription/cds:cds"),
          mappedPort = Reference(gfp.cds),
          mappedTo = Reference(gfpCDS_sig)
        )
      )
    )


    (gfpProduction_module,pspaSPromoterComp_port)

  }


  def pspaSActivationModule ={

    val pspaSPromoterComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module/pspaS_promoter"),
      instantiated = pspaSPromoterComp.identity
    )

    val pspaSPromoterComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module/pspaS_promoter_port"),
      exposes = Reference(pspaSPromoterComp_sig),
      directionality = OUT
    )

    val spaRProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module/SpaR_protein"),
      instantiated = spaRProteinComp.identity
    )

    val spaRProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module/spaRProtein_port"),
      exposes = Reference(spaRProteinComp_sig),
      directionality = IN
    )

    val pspaSActivation_module = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module"),
      name = Some("pspaS_activation_module"),
      signals = Seq(pspaSPromoterComp_sig, spaRProteinComp_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module/transcriptional_activation"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module/transcription/TF"),
              role = URI("http://purl.obolibrary.org/obo/SO_0000459"),//stimulator
              participant = Reference(spaRProteinComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#pspaS_activation_module/transcription/promoter"),
              role = URI("http://purl.obolibrary.org/obo/SO_0000167"), // SBO:Promoter
              participant = Reference(pspaSPromoterComp_sig)
            )
          )
        )
      ),
      ports = Seq(pspaSPromoterComp_port,spaRProteinComp_port)
    )

    (pspaSActivation_module,pspaSPromoterComp_port,spaRProteinComp_port)

  }

  def spaRActivationModule ={

    val spaRProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_protein"),
      instantiated = spaRProteinComp.identity
    )

    val spaRProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_protein_port"),
      exposes = Reference(spaRProteinComp_sig),
      directionality = IN_OUT
    )

    val spaR_P_ProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_p_protein"),
      instantiated = spaRProteinComp.identity
    )

    val spaR_P_ProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_p_protein_port"),
      exposes = Reference(spaR_P_ProteinComp_sig),
      directionality = IN_OUT
    )


    val spaKProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_protein"),
      instantiated = spaKProteinComp.identity
    )

    val spaKProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_protein_port"),
      exposes = Reference(spaKProteinComp_sig),
      directionality = IN_OUT
    )

    val spaK_P_ProteinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_p_protein"),
      instantiated = spaKProteinComp.identity
    )

    val spaK_P_ProteinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_p_protein_port"),
      exposes = Reference(spaK_P_ProteinComp_sig),
      directionality = IN_OUT
    )

    val subtilinComp_sig = Signal(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/subtilin"),
      instantiated = subtilinComp.identity
    )

    val subtilinComp_port= Port(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/subtilin_port"),
      exposes = Reference(subtilinComp_sig),
      directionality = IN
    )


    val spaRActivation_module = Module(
      identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module"),
      name = Some("spaR activation module"),
      signals = Seq(spaRProteinComp_sig, spaR_P_ProteinComp_sig,spaKProteinComp_sig,spaK_P_ProteinComp_sig,subtilinComp_sig),
      interactions = Seq(
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_activation"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_activation/SpaK"),
              role = URI("http://purl.obolibrary.org/obo/MI_0843"),//phosphate acceptor
              participant = Reference(spaKProteinComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_activation/SpaK_P"),
              role = URI("http://purl.obolibrary.org/obo/SBO_0000011"), // SBO:Product
              participant = Reference(spaK_P_ProteinComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaK_activation/Subtilin"),
              role = URI("http://purl.obolibrary.org/obo/SBO_0000019"), // modifier
              participant = Reference(subtilinComp_sig)
            )
          )
        ),
        Interaction(
          identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_activation"),
          participations = Seq(
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_activation/SpaK_P"),
              role = URI("http://purl.obolibrary.org/obo/MI_0842"),//phosphate donor
              participant = Reference(spaK_P_ProteinComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_activation/SpaR"),
              role = URI("http://purl.obolibrary.org/obo/MI_0843"), // phosphate acceptor
              participant = Reference(spaRProteinComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_activation/SpaK"),
              role = URI("http://purl.obolibrary.org/obo/SBO_0000011"), // product
              participant = Reference(spaKProteinComp_sig)
            ),
            Participation(
              identity = URI("http://turingatemyhamter.co.uk/example#spaR_activation_module/SpaR_activation/SpaR_P"),
              role = URI("http://purl.obolibrary.org/obo/SBO_0000011"), // product
              participant = Reference(spaR_P_ProteinComp_sig)
            )
          )
        )
      ),
      ports = Seq(spaRProteinComp_port,spaR_P_ProteinComp_port, spaKProteinComp_port,spaK_P_ProteinComp_port,subtilinComp_port)
    )

    (spaRActivation_module,spaRProteinComp_port,spaR_P_ProteinComp_port, spaKProteinComp_port,spaK_P_ProteinComp_port,subtilinComp_port)

  }


}



case class ProteinEncodingCdsModule(m: Module, cds: Port[Signal], protein: Port[Signal])