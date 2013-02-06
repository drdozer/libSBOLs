package uk.co.turingatemyhamster.sbols
package sequence

import identified.AsDescribed
import java.net.URI

trait AsDnaComponent[DC] extends AsDescribed[DC] {
  type _DS
  type _SA

  def sequenceType(dc: DC): Set[URI]
  def dnaSequence(dc: DC): Option[_DS]
  def annotations(dc: DC): Set[_SA]
}
