package uk.co.turingatemyhamster

/**
 *
 *
 * @author Matthew Pocock
 */
package object rdfPickler {
  implicit class FunctionPickler[E, P](val _f: E => P) extends AnyVal {
    def picklePropertyAs[PM](pm: PM)
                            (implicit pmM: PM => PropertyMaker, ep: PropertyMaker => RdfPropertyCardinalityResolver[E, P])
    : RdfEntityPickler[E] =
      RdfEntityPickler.byProperty(_f)(ep(pm).resolve)

    def pickleValue[PM](implicit ep: RdfEntityCardinalityResolver[P]): RdfEntityPickler[E] = ep.resolve comap _f
  }
}
