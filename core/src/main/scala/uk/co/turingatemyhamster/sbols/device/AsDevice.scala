package uk.co.turingatemyhamster
package sbols
package device

import identified.{AsIdentified, AsDescribed}
import java.net.URI
import s.Port
import sequence.AsDnaComponent

trait AsDevice[D] extends AsDescribed[D] {
  type _P

  implicit def AsPort: AsPort[_P]

  def function(d: D): URI
  def inputs(d: D): Set[_P]
  def outputs(d: D): Set[_P]
}

object AsDevice {

  implicit class DeviceOps[D](device: D)(implicit val asDevice: AsDevice[D]) {
    def portsByRole(role: URI): Set[asDevice._P] = {
      for(p <- asDevice.inputs(device) ++ asDevice.outputs(device) if asDevice.AsPort.role(p) == role) yield p
    }
  }

}

trait AsAtomicDevice[AD] extends AsDevice[AD] {
  type _DC

  implicit def AsDnaComponent : AsDnaComponent[_DC]

  def part(d: AD): _DC
}

trait AsCompositeDevice[CD] extends AsDevice[CD] {
  type _D
  type _C

  implicit def AsDevice : AsDevice[_D]
  implicit def AsConnection : AsConnection[_C]

  def subDevices(cd: CD): Set[_D]
  def connections(cd: CD): Set[_C]
}

trait AsConnection[C] extends AsIdentified[C] {
  type _P

  implicit def AsPort: AsPort[_P]

  def input(c: C): _P
  def output(c: C): _P
}

trait AsPort[P] extends AsIdentified[P] {
  def role(p: P): URI
}

object AsPort {
  def asPort[P](p: P)(implicit ap: AsPort[P]): AsPort[P] = ap
}