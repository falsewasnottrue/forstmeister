package de.forstmeister.parser

import scala.io.Source

object Parser {

  def parse(filename: String): Spec = {
    val text = Source.fromResource(filename).getLines.mkString(" ")

    val tokens = Scanner.scan(text)

    val (name, tks) = parseName(tokens)
    val (mode, _) = parseMode(tks)
    Spec(name, mode)
  }

  def parseName(tokens: Seq[Token]): (String, Seq[Token]) = tokens match {
    case TK_NAME +: TK_IDENT(name) +: moreTokens ⇒ (name, moreTokens)
    case _ ⇒ throw new IllegalArgumentException(s"Name declaration missing")
  }

  def parseMode(tokens: Seq[Token]): (Mode, Seq[Token]) = tokens match {
    case TK_MODE +: TK_COMPLETION +: moreTokens ⇒ (MODE_COMPLETION, moreTokens)
    case TK_MODE +: TK_CONFLUENCE +: moreTokens ⇒ (MODE_CONFLUENCE, moreTokens)
    case TK_MODE +: TK_CONVERGENCE +: moreTokens ⇒ (MODE_CONVERGENCE, moreTokens)
    case TK_MODE +: TK_PROOF +: moreTokens ⇒ (MODE_PROOF, moreTokens)
    case TK_MODE +: TK_REDUCTION +: moreTokens ⇒ (MODE_REDUCTION, moreTokens)
    case TK_MODE +: TK_TERMINATION +: moreTokens ⇒ (MODE_TERMINATION, moreTokens)

    case _ ⇒ throw new IllegalArgumentException(s"Mode declation missing")
  }
}
