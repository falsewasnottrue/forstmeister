package de.forstmeister.domain

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class TermSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  override def beforeEach(): Unit = VariableSymbol.reset()

  "Terms" should "be possibly a variable" in {
    val x = VariableSymbol("x")
    val term = Term(x)

    term.topSymbol should be(x)
    term.subterms should be(Nil)
    term.isVariable should be(true)
    term.toString should be("x")
  }

  it should "be possibly a tree" in {
    val x = VariableSymbol("x1")
    val y = VariableSymbol("y1")
    val f = FunctionSymbol("f", 2)
    val term = Term(f, Seq(Term(x),Term(y)))

    term.topSymbol should be(f)
    term.subterms should be(Seq(Term(x),Term(y)))
    term.isVariable should be(false)
    term.toString should be("f(x1,y1)")
  }

  it should "fail if arity does not match" in {
    val f = FunctionSymbol("f2", 2)
    val x = VariableSymbol("x2")

    try {
      /*val term =*/ Term(f, Seq(Term(x)))
      true should be(false)
    } catch {
      case e: IllegalArgumentException ⇒ e.getMessage should be("Number of subterms 1 does not equal function arity 2")
    }
  }

  it should "be possibly a constant function" in {
    val c = FunctionSymbol("c", 0)
    val term = Term(c)

    term.topSymbol should be(c)
    term.subterms should be(Nil)
    term.isVariable should be(false)
    term.toString should be("c")
  }
}
