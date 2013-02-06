package uk.co.turingatemyhamster.sbols
package sequence

import identified.AsIdentified

trait AsSequenceAnnotation[SA] extends AsIdentified[SA] {
  type _DC
  type _SA

  def bioStart(sa: SA): Option[Int]
  def bioEnd(sa: SA): Option[Int]
  def strand(sa: SA): Option[Strand]

  def precedes(sa: SA): Set[_SA]
  def subComponent(sa: SA): _DC
}

sealed trait Strand
object Strand {
  case object + extends Strand
  case object - extends Strand
}