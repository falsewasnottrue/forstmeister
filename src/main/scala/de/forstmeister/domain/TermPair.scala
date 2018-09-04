package de.forstmeister.domain

sealed trait TermPair {
  def left: Term
  def right: Term

  // TODO getDifferentVariables Liefert eine Iterator uber alle verschiedenen Variablensymbole in dem TermPar zurueck.
  // TODO makeVarDisjoint Macht das Termpar variablendisjunkt zu allen anderen. Dies geschieht z.Zt. indem alle Variablen neu besetzt werden.

  // TODO asEquation
  // TODO asRule
  // TODO asCriticalPair
}

case class Rule(left: Term, right: Term) extends TermPair

case class Equation(left: Term, right: Term) extends TermPair

case class CriticalPair(left: Term, right: Term) extends TermPair
