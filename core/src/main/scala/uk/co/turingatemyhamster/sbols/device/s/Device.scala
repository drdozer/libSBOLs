package uk.co.turingatemyhamster.sbols
package device
package s

import java.net.URI
import identified.s.Described
import identified.s.Identified

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
  trait DeviceAsDevice[D <: Device[P], P] extends AsDevice[D, P] with Described.DescribedAsDescribed[D] {
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
  trait AtomicDeviceAsAtomicDevice[AD <: AtomicDevice[P, DC], P, DC]
    extends AsAtomicDevice[AD, P, DC]
    with Device.DeviceAsDevice[AD, P]
  {
    final def part(d: AD) = d.part
  }

  implicit def atomicDeviceAsAtomicDevice[AD <: AtomicDevice[P, DC], P, DC] =
    new AtomicDeviceAsAtomicDevice[AD, P, DC] {}
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
  trait CompositeDeviceAsCompositeDevice[CD <: CompositeDevice[P, D, C], P, D, C]
    extends AsCompositeDevice[CD, P, D, C]
    with Device.DeviceAsDevice[CD, P]
  {
    final def subDevices(cd: CD) = cd.subDevices
    final def connections(cd: CD) = cd.connections
  }

  implicit def compositeDeviceAsCompositeDevice[CD <: CompositeDevice[P, D, C], P, D, C] =
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
  trait ConnectionAsConnection[C <: Connection[P], P] extends AsConnection[C, P] with Identified.IdentifiedAsIdentified[C] {
    final def input(c: C) = c.input
    final def output(c: C) = c.output
  }
}

case class ConnectionImpl[P <: Port](uri: URI, input: P, output: P) extends Identified


trait Port extends Identified {
  def role: URI
}

object Port {
  trait PortAsPort[P <: Port] extends AsPort[P] with Identified.IdentifiedAsIdentified[P] {
    final def role(p: P) = p.role
  }

  implicit object portAsPort extends PortAsPort[Port] {}
}

case class PortImpl(uri: URI, role: URI) extends Port