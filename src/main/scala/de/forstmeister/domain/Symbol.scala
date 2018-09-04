package de.forstmeister.domain

import scala.collection.mutable

sealed trait Symbol {
  def id: Int
  def name: String

  def isVariable: Boolean

  override def toString: String = name

  def equals(other: Symbol): Boolean = isVariable == other.isVariable && id == other.id
}


class VariableSymbol(val name: String, val id: Int) extends Symbol {
  import de.forstmeister.domain.VariableSymbol.{counter, usedNames}

  if (usedNames.keySet.contains(name)) {
    throw new IllegalArgumentException(s"Variable with Name $name already exists")
  }
  usedNames += (name → this)

  override def isVariable: Boolean = true
}

object VariableSymbol {
  def apply(name: String) = new VariableSymbol(name, nextId)
  def apply() = {
    val id = VariableSymbol.nextId
    new VariableSymbol("x" + id, id)
  }

  private val usedNames = mutable.HashMap[String, VariableSymbol]()
  def findByName(name: String): Option[VariableSymbol] = usedNames.get(name)

  private var counter: Int = 0
  private def nextId = {
    counter = counter + 1
    counter
  }
}


class FunctionSymbol(val name: String, val id: Int, val arity: Int) extends Symbol {
  override def isVariable: Boolean = false
}

object FunctionSymbol {
  def apply(name: String) = new FunctionSymbol(name, nextId, 0)
  def apply(name: String, arity: Int) = new FunctionSymbol(name, nextId, arity)

  private var counter: Int = 0
  private def nextId = {
    counter = counter + 1
    counter
  }
}
