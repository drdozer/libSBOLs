package uk.co.turingatemyhamster.sbols.identified

import org.specs2.mutable.SpecificationWithJUnit
import java.net.URI

class IdentifiedTest extends SpecificationWithJUnit {
  lazy val uri1 = new URI("http://www.google.com")
  lazy val uri2 = new URI("http://www.wikipedia.org")

  lazy val s_identified1 = s.IdentifiedImpl(uri1)
  lazy val s_identified2 = s.IdentifiedImpl(uri2)
  lazy val s_identified2_copy = s.IdentifiedImpl(uri2)

  lazy val j_identified1 = new j.IdentifiedImpl(uri1)
  lazy val j_identified2 = new j.IdentifiedImpl(uri2)
  lazy val j_identified2_copy = new j.IdentifiedImpl(uri2)

  "The scala implementation of Identified" should {
    "Have the assigned URI" in { s_identified1.uri == uri1 }
    "Be equal when the URI is equal" in { s_identified2 mustEqual s_identified2_copy }
    "Be unequal when the URI is unequal" in { s_identified1 mustNotEqual s_identified2 }
    "Have an AsIdentified instance" in {
      "which exists" in { AsIdentified.asIdentified(s_identified1) mustNotEqual null }
      "returns the appropriate uri" in {
        AsIdentified.asIdentified(s_identified1).uri(s_identified1) mustEqual s_identified1.uri
      }
    }
  }

  "The scala implementation of Identified" should {
    "Have the assigned URI" in { j_identified1.getURI == uri1 }
    "Be equal when the URI is equal" in { j_identified2 mustEqual j_identified2_copy }
    "Be unequal when the URI is unequal" in { j_identified1 mustNotEqual j_identified2 }
    "Have an AsIdentified instance" in {
      "which exists" in { AsIdentified.asIdentified(j_identified1) mustNotEqual null }
      "returns the appropriate uri" in {
        AsIdentified.asIdentified(j_identified1).uri(j_identified1) mustEqual j_identified1.getURI
      }
    }
  }

  "AsIdentified instances provide sameEntity that" should {
    import AsIdentified._
    "Match a scala instance to itself" in {
      s_identified1 sameEntity s_identified1
    }

    "Match a scala instance to a scala instance with the same URI" in {
      s_identified2 sameEntity s_identified2_copy
    }

    "Not match a scala instance to a scala instance with a different URI" in {
      !(s_identified1 sameEntity s_identified2)
    }

    "Match a java instance to itself" in {
      j_identified1 sameEntity j_identified1
    }

    "Match a java instance to a java instance with the same URI" in {
      j_identified2 sameEntity j_identified2_copy
    }

    "Not match a java instance to a java instance with a different URI" in {
      !(j_identified1 sameEntity j_identified2)
    }

    "Match a java instance to a scala instance with the same URI" in {
      j_identified2 sameEntity s_identified2_copy
    }

    "Not match a java instance to a scala instance with a different URI" in {
      !(j_identified1 sameEntity s_identified2)
    }

    "Match a scala instance to a java instance with the same URI" in {
      s_identified2 sameEntity j_identified2_copy
    }

    "Not match a scala instance to a java instance with a different URI" in {
      !(s_identified1 sameEntity j_identified2)
    }
  }
}
