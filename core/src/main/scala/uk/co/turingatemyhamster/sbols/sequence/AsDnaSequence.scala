package uk.co.turingatemyhamster.sbols
package sequence

import identified.AsIdentified

trait AsDnaSequence[DS] extends AsIdentified[DS] {
  def nucleotides(ds: DS): String
}
