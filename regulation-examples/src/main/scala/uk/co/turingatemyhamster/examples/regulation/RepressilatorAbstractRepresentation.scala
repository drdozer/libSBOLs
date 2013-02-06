package uk.co.turingatemyhamster
package examples.regulation

import sbols.sequence.s.{DnaSequence, SequenceAnnotation, DnaComponent, DnaComponentImpl}
import java.net.URI
import sbols.device.s._
import sbols.device.s.CompositeDeviceImpl
import sbols.device.{AsPort, AsDevice}
import sbols.sequence.AsDnaComponent

/**
 * An example to demonstrate representing the Repressilator.
 *
 * @author Matthew Pocock
 */
object RepressilatorAbstractRepresentation {
  type DC = DnaComponent[DnaSequence, SequenceAnnotation.Canonical]

  def main(args: Array[String]) {

    // our DNA components - 3 promoters, 4 CDS

    val plLac01: DC = DnaComponentImpl(
      uri          = "usecase:repressilator/PLlac01",
      displayId    = "PLlac01",
      name         = None,
      description  = None,
      sequenceType = Set("so:promoter"),
      dnaSequence  = None,
      annotations  = Set())

    val tetR_lite : DC = DnaComponentImpl(
      uri          = "usecase:repressilator/tetR-lite",
      displayId    = "tetR-lite",
      name         = None,
      description  = None,
      sequenceType = Set("so:CDS"),
      dnaSequence  = None,
      annotations  = Set())

    val plTet01 : DC = DnaComponentImpl(
      uri          = "usecase:repressilator/PLtet01",
      displayId    = "PL-tet01",
      name         = None,
      description  = None,
      sequenceType = Set("so:promoter"),
      dnaSequence  = None,
      annotations  = Set())

    val gfpAav : DC = DnaComponentImpl(
      uri = "usecase:repressilator/gfp-aav",
      displayId = "gfp-aav",
      name         = None,
      description  = None,
      sequenceType = Set("so:CDS"),
      dnaSequence  = None,
      annotations  = Set())

    val lambdaClLite : DC = DnaComponentImpl(
      uri          = "usecase:repressilator/lambda_cl-lite",
      displayId    = "lambda cl-lite",
      name         = None,
      description  = None,
      sequenceType = Set("so:CDS"),
      dnaSequence  = None,
      annotations  = Set())

    val lambdaPR : DC = DnaComponentImpl(
      uri          = "usecase:represssilator/lambdaPR",
      displayId    = "lambdaPR",
      name         = None,
      description  = None,
      sequenceType = Set("so:promoter"),
      dnaSequence  = None,
      annotations  = Set())

    val lacI_lite : DC = DnaComponentImpl(
      uri          = "usecase:repressilator/lacI-lite",
      displayId    = "lacI-lite",
      name         = None,
      description  = None,
      sequenceType = Set("so:CDS"),
      dnaSequence  = None,
      annotations  = Set())

    // some functions
    val repressible_promoter : URI = "sbol:device/function/repressible_promoter"
    val repressor_of_transcription_initiation : URI = "sbol:device/function/repressor_of_transcription_initiation"
    val visual_reporter : URI = "sbol:device/function/visual_reporter"

    val repressible_repressor : URI = "sbol:device/function/repressible_repressor"

    // some roles
    val repressed_role : URI = "sbol:device/role/represed"
    val repressor_role : URI = "sbol:device/role/repressor"
    val pops_in_role : URI = "sbol:device/role/pops_in"
    val pops_out_role : URI = "sobl:device/role/pops_out"

    // Each component as an atomic device
    val pLac01_device = AtomicDevice(
      uri      = "usecase:repressilator/PLlac01_device",
      function = repressible_promoter,
      inputs   = Set(Port(uri = "usecase:repressilator/PLlac01_device/repressed", role = repressed_role)),
      outputs  = Set(Port(uri = "usecase:repressilator/PLlac01_device/pops_out", role = pops_out_role)),
      part     = plLac01)

    val tetR_lite_device = AtomicDevice(
      uri      = "usecase:repressilator/tetR-lite_device",
      function = repressor_of_transcription_initiation,
      inputs   = Set(Port(uri = "usecase:repressilator/tetR-lite_device/pops_in", role = pops_in_role)),
      outputs  = Set(Port(uri = "usecase:repressilator/tetR-lite_device/represor", role = repressor_role)),
      part     = tetR_lite)

    val plTet01_device = AtomicDevice(
      uri      = "usecase:repressilator/PLtet01_device",
      function = repressible_promoter,
      inputs   = Set(Port(uri = "usecase:repressilator/PLtet01_device/repressed", role = repressed_role)),
      outputs  = Set(Port(uri = "usecase:repressilator/PLtet01_device/pops_out", role = pops_out_role)),
      part     = plTet01)

    val gfpAav_device = AtomicDevice(
      uri      = "usecase:repressilator/gfp-aav_device",
      function = repressor_of_transcription_initiation,
      inputs   = Set(Port(uri = "usecase:repressilator/gfp-aav_device/pops_in", role = pops_in_role)),
      outputs  = Set(Port(uri = "usecase:repressilator/gfp-aav_device/green_pigment", role = visual_reporter)),
      part     = gfpAav)

    val lambdaClLite_device = AtomicDevice(
      uri      = "usecase:repressilator/lambda_cl-lite_device",
      function = repressor_of_transcription_initiation,
      inputs   = Set(Port(uri = "usecase:repressilator/lambda_cl-lite_device/pops_in", role = pops_in_role)),
      outputs  = Set(Port(uri = "usecase:repressilator/lambda_cl-lite_device/represor", role = repressor_role)),
      part     = lambdaClLite)

    val lambdaPR_device = AtomicDevice(
      uri      = "usecase:repressilator/lambdaPR_device",
      function = repressible_promoter,
      inputs   = Set(Port(uri = "usecase:repressilator/lambdaPR_device/repressed", role = repressed_role)),
      outputs  = Set(Port(uri = "usecase:repressilator/lambdaPR_device/pops_out", role = pops_out_role)),
      part     = lambdaPR)

    val lacI_lite_device = AtomicDevice(
      uri      = "usecase:repressilator/lacI-lite_device",
      function = repressor_of_transcription_initiation,
      inputs   = Set(Port(uri = "usecase:repressilator/lacI-lite_device/pops_in", role = pops_in_role)),
      outputs  = Set(Port(uri = "usecase:repressilator/lacI-lite_device/represor", role = repressor_role)),
      part     = lacI_lite)

    AsPort.asPort(Port("", "")).role(Port("", ""))
    val ap = AsPort.asPort(PortImpl("", ""))
    val apI = implicitly[AsPort[PortImpl]]

    import AsDevice._

    implicitly[AsDnaComponent[DC]]
    DnaComponent.dnaComponentAsDnaComponent[DC, DnaSequence, SequenceAnnotation.Canonical]

    DeviceOps(pLac01_device)
    DeviceOps(pLac01_device)(implicitly[AsDevice[AtomicDeviceImpl[PortImpl, DC]]])
    DeviceOps(pLac01_device)(AtomicDevice.atomicDeviceAsAtomicDevice[AtomicDeviceImpl[PortImpl, DC], PortImpl, DC])
    pLac01_device portsByRole repressor_role

    // transcriptional units
    val tetR_regulon_device = new CompositeDevice[PortImpl, AtomicDeviceImpl[PortImpl, DC], ConnectionImpl[PortImpl]] {
      self =>

      private val repressedPort = Port(uri = "usecase:repressilator/tetR_regulon_device/represed", role = repressed_role)
      private val repressorPort = Port(uri = "usecase:repressilator/tetR_regulon_device/repressor", role = repressor_role)

      private val plac01_repressed = (pLac01_device portsByRole repressed_role).head.asInstanceOf[PortImpl]
      private val tetR_lite_repressor = (tetR_lite_device portsByRole repressor_role).head.asInstanceOf[PortImpl]
      private val pLac01_pops_out = (pLac01_device portsByRole pops_out_role).head.asInstanceOf[PortImpl]
      private val tetR_lite_pops_in = (tetR_lite_device portsByRole pops_in_role).head.asInstanceOf[PortImpl]

      val      uri: URI    = "usecase:repressilator/tetR_regulon_device"
      val      displayId   = "tetR regulon"
      val      name        = None
      val      description = None
      val      function    = repressible_repressor
      val      inputs      = Set(repressedPort)
      val      outputs     = Set(repressorPort)
      val      subDevices  = Set(pLac01_device, tetR_lite_device)
      lazy val connections = Set[ConnectionImpl[PortImpl]](
        ConnectionImpl(
          uri    = "usecase:repressilator/tetR_regulon_device/rout_repressed",
          input  = plac01_repressed,
          output = repressedPort),
        ConnectionImpl(
          uri    = "usecase:repressilator/tetR_regulon_device/rout_repressor",
          input  = tetR_lite_repressor,
          output = repressorPort),
        ConnectionImpl(
          uri    = "usecase:repressilator/tetR_regulon_device/rout_pops",
          input  = pLac01_pops_out,
          output = tetR_lite_pops_in)
        )
    }
  }

  private implicit def URI(s: String): URI = new URI(s) // convenience
}
