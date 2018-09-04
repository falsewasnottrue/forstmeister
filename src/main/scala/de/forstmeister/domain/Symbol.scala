package de.forstmeister.domain

import de.forstmeister.domain.VariableSymbol.usedNames

import scala.collection.mutable

sealed trait Symbol {
  def id: Int
  def name: String

  def isVariable: Boolean

  override def toString: String = name

  def equals(other: Symbol): Boolean = isVariable == other.isVariable && id == other.id
}

class VariableSymbol(val name: String, val id: Int) extends Symbol {
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

  private var counter: Int = 0
  private val usedNames = mutable.HashMap[String, VariableSymbol]()

  def findByName(name: String): Option[VariableSymbol] = usedNames.get(name)

  def nextId = {
    counter = counter + 1
    counter
  }
}
// TODO FunctionSymbo
