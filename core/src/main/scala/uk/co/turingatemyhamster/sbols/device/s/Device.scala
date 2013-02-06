package uk.co.turingatemyhamster.sbols
package device
package s

import java.net.URI
import identified.s.Described
import identified.s.Identified
import sequence.s.DnaComponent
import sequence.AsDnaComponent

/**
 * A Device is the unit of functional design composition.
 *
 * @author Matthew Pocock
 */
sealed trait Device[P] extends Described {
  def function: URI
  def inputs: Set[P]
  def outputs: Set[P]
}

object Device {
  abstract class DeviceAsDevice[D <: Described, P](implicit castD: D <:< Device[P], asPort: AsPort[P])
    extends Described.DescribedAsDescribed[D] with AsDevice[D]
  {
    type _P = P

    implicit def AsPort = asPort

    final def function(d: D) = d.function
    final def inputs(d: D) = d.inputs
    final def outputs(d: D) = d.outputs
  }
}


/**
 * A device that presents a functional view of a DnaComponent.
 *
 * @author Matthew Pocock
 */
trait AtomicDevice[P, DC] extends Device[P] {
  def part: DC
}

object AtomicDevice {
  abstract class AtomicDeviceAsAtomicDevice[AD <: Described, P, DC]
  (implicit adCast: AD <:< AtomicDevice[P, DC], asPort: AsPort[P], asDnaComponent: AsDnaComponent[DC])
    extends Device.DeviceAsDevice[AD, P]
    with AsAtomicDevice[AD]
  {
    type _DC = DC

    implicit def AsDnaComponent = asDnaComponent

    final def part(d: AD) = d.part
  }

  implicit def atomicDeviceAsAtomicDevice[AD <: Described, P, DC]
  (implicit adCast: AD <:< AtomicDevice[P, DC], asPort: AsPort[P], asDnaComponent: AsDnaComponent[DC]) =
    new AtomicDeviceAsAtomicDevice[AD, P, DC] {}

  def apply[P, DC <: Described]
  (uri: URI, function: URI, inputs: Set[P], outputs: Set[P], part: DC): AtomicDeviceImpl[P, DC] =
    AtomicDeviceImpl(uri, part.displayId, part.name, part.description, function, inputs, outputs, part)
}

case class AtomicDeviceImpl[P, DC](uri: URI,
                                   displayId: String,
                                   name: Option[String],
                                   description: Option[String],
                                   function: URI,
                                   inputs: Set[P],
                                   outputs: Set[P],
                                   part: DC) extends AtomicDevice[P, DC]


trait CompositeDevice[P, D, C] extends Device[P] {
  def subDevices: Set[D]
  def connections: Set[C]
}

object CompositeDevice {
  abstract class CompositeDeviceAsCompositeDevice[CD <: CompositeDevice[P, D, C], P, D, C]
  (implicit asPort: AsPort[P], asDevice: AsDevice[D], asConnection: AsConnection[C])
    extends Device.DeviceAsDevice[CD, P]
    with AsCompositeDevice[CD]
  {
    type _D = D
    type _C = C


    implicit def AsDevice = asDevice
    implicit def AsConnection = asConnection

    final def subDevices(cd: CD) = cd.subDevices
    final def connections(cd: CD) = cd.connections
  }

  implicit def compositeDeviceAsCompositeDevice[CD <: CompositeDevice[P, D, C], P, D, C]
  (implicit asPort: AsPort[P], asDevice: AsDevice[D], asConnection: AsConnection[C]) =
    new CompositeDeviceAsCompositeDevice[CD, P, D, C] {}

}

case class CompositeDeviceImpl[P, D, C](uri: URI,
                                        displayId: String,
                                        name: Option[String],
                                        description: Option[String],
                                        function: URI,
                                        inputs: Set[P],
                                        outputs: Set[P],
                                        subDevices: Set[D],
                                        connections: Set[C]) extends CompositeDevice[P, D, C]


trait Connection[P] extends Identified {
  def input: P
  def output: P
}

object Connection {
  abstract class ConnectionAsConnection[C <: Connection[P], P](implicit asPort: AsPort[P])
    extends Identified.IdentifiedAsIdentified[C]
    with AsConnection[C]
  {
    type _P = P

    implicit def AsPort = asPort

    final def input(c: C) = c.input
    final def output(c: C) = c.output
  }
}

case class ConnectionImpl[P <: Port](uri: URI, input: P, output: P) extends Identified


trait Port extends Identified {
  def role: URI
}

object Port {
  abstract class PortAsPort[P <: Port]
    extends Identified.IdentifiedAsIdentified[P]
    with AsPort[P]
  {
    final def role(p: P) = p.role
  }

  implicit def portAsPort[P <: Port]: PortAsPort[P] = new PortAsPort[P] {}


  def apply(uri: URI, role: URI): PortImpl = PortImpl(uri, role)
}

case class PortImpl(uri: URI, role: URI) extends Port