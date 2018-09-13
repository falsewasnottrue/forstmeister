package de.forstmeister.domain

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class SymbolSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  override def beforeEach(): Unit = VariableSymbol.reset()

  "Variables" should "be variables" in {
    val v1 = VariableSymbol("v1")
    v1.isVariable should be(true)
    v1.name should be("v1")
  }

  it should "autogenerate a name" in {
    val v2 = VariableSymbol()
    v2.isVariable should be(true)
    v2.name should be("x" + v2.id)
  }

  it should "not allow duplicate names" in {
    try {
      /*val v1 = */ VariableSymbol("x")
      /*val v2 = */ VariableSymbol("x")
      true should be(false)
    } catch {
      case e: IllegalArgumentException ⇒
        e.getMessage should be("Variable with Name x already exists")
    }
  }

  it should "allow to find variables by name" in {
    val v1 = VariableSymbol("y")
    val Some(v2) = VariableSymbol.findByName("y")

    v2 should be(v1)
  }

  it should "return None for non-existing variables" in {
    val v1 = VariableSymbol.findByName("gibtsnicht")
    v1 should be(None)
  }

  "FunctionSymbols" should "not be a variable" in {
    val f = FunctionSymbol("f", 1)
    f.isVariable should be(false)
  }
}
