package uk.co.turingatemyhamster.sbols
package sequence

import identified.AsDescribed
import java.net.URI

trait AsDnaComponent[DC, DS, SA] extends AsDescribed[DC] {
  def sequenceType(dc: DC): Set[URI]
  def dnaSequence(dc: DC): Option[DS]
  def annotations(dc: DC): Set[SA]
}
