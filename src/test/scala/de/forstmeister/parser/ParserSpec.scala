package de.forstmeister.parser

import org.scalatest.{FlatSpec, Matchers}

class ParserSpec extends FlatSpec with Matchers {

  "Parser" should "be able to read a completion file" in {
    val spec = Parser.parse("specs/AbelGruppe.cp")

    spec.name should be("group")
    spec.mode should be(MODE_COMPLETION)
    // spec.sorts
    // spec.signature
    // spec.variables ??
    // spec.ordering
    // spec.equations
  }
}
