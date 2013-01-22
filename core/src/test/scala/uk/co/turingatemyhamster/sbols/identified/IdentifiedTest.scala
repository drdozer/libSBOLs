package uk.co.turingatemyhamster.sbols.identified

import org.specs2.mutable.SpecificationWithJUnit
import java.net.URI
import s.IdentifiedImpl

class IdentifiedTest extends SpecificationWithJUnit {
  lazy val uri1 = new URI("http://www.google.com")
  lazy val uri2 = new URI("http://www.wikipedia.org")

  lazy val identified1 = IdentifiedImpl(uri1)
  lazy val identified2 = IdentifiedImpl(uri2)
  lazy val identified2_copy = IdentifiedImpl(uri2)

  "The scala implementation of Identified" should {
    "Have the assigned URI" in { identified1.uri == uri1 }
    "Be equal when the URI is equal" in { identified2 mustEqual identified2_copy }
    "Be unequal when the URI is unequal" in { identified1 mustNotEqual identified2 }
    "Have an AsIdentified instance" in {
      AsIdentified.asIdentified(identified1) mustNotEqual null
    }
  }

}
