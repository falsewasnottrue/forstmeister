package de.forstmeister.parser

import de.forstmeister.domain.{TermOrdering, TermPair}

sealed trait Mode
case object MODE_COMPLETION extends Mode
case object MODE_CONFLUENCE extends Mode
case object MODE_CONVERGENCE extends Mode
case object MODE_PROOF extends Mode
case object MODE_REDUCTION extends Mode
case object MODE_TERMINATION extends Mode

case class Spec(
  name: String,
  // TODO remove default values
  mode: Mode = MODE_COMPLETION,
  signature: Signature = new Signature(),
  equations: Seq[TermPair] = Nil,
  goals: Seq[TermPair] = Nil,
  ordering: TermOrdering = new TermOrdering
)
