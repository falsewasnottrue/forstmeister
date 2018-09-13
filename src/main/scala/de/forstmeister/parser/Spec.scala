package de.forstmeister.parser

import de.forstmeister.domain.{Sort, TermOrdering, TermPair}

sealed trait Mode
case object MODE_COMPLETION extends Mode
case object MODE_CONFLUENCE extends Mode
case object MODE_CONVERGENCE extends Mode
case object MODE_PROOF extends Mode
case object MODE_REDUCTION extends Mode
case object MODE_TERMINATION extends Mode

case class Spec(
  name: String,
  mode: Mode,
  sorts: Seq[Sort],
  // FIXME remove default values
  signature: Signature = new Signature(),
  equations: Seq[TermPair] = Nil,
  goals: Seq[TermPair] = Nil,
  ordering: TermOrdering = new TermOrdering
)
