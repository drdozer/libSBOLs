package uk.co.turingatemyhamster.sbols.sequence

import uk.co.turingatemyhamster.sbols.identified.AsDescribed

/**
 * Created with IntelliJ IDEA.
 * User: nmrp3
 * Date: 17/01/13
 * Time: 00:25
 * To change this template use File | Settings | File Templates.
 */
trait AsCollection[C, DC] extends AsDescribed[C] {
  def components(c: C): Seq[DC]
}