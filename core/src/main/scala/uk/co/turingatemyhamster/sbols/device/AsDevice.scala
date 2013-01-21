package uk.co.turingatemyhamster
package sbols
package device

import identified.{AsIdentified, AsDescribed}
import java.net.URI
import s.Port

trait AsDevice[D, P] extends AsDescribed[D] {
  def function(d: D): URI
  def inputs(d: D): Set[P]
  def outputs(d: D): Set[P]
}

trait AsAtomicDevice[AD, P, DC] extends AsDevice[AD, P] {
  def part(d: AD): DC
}

trait AsCompositeDevice[CD, P, D, C] extends AsDevice[CD, P] {
  def subDevices(cd: CD): Set[D]
  def connections(cd: CD): Set[C]
}

trait AsConnection[C, P] extends AsIdentified[C] {
  def input(c: C): P
  def output(c: C): P
}
trait AsPort[P] extends AsIdentified[P] {
  def role(p: P): URI
}
