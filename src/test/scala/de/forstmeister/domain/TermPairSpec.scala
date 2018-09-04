package de.forstmeister.domain

import org.scalatest.{FlatSpec, Matchers}

class TermPairSpec extends FlatSpec with Matchers {

  val term1 = Term(VariableSymbol("x3"))
  val term2 = Term(VariableSymbol("x4"))

  "Term pairs" should "be possibly rules" in {
    val rule: TermPair = Rule(term1, term2)

    rule.left should be(term1)
    rule.right should be(term2)
  }

  it should "be possibly equations" in {
    val equation: TermPair = Equation(term1, term2)

    equation.left should be(term1)
    equation.right should be(term2)
  }

  it should "be possibly critical pairs" in {
    val cp: TermPair = CriticalPair(term1, term2)

    cp.left should be(term1)
    cp.right should be(term2)
  }
}
