package de.forstmeister.parser

import scala.io.Source

object Parser {

  def parse(filename: String): Spec = {
    val text = Source.fromResource(filename).getLines.mkString(" ")

    val tokens = Scanner.scan(text)

    val (name, _) = parseName(tokens)

    Spec(name)
  }

  def parseName(tokens: Seq[Token]): (String, Seq[Token]) = tokens match {
    case TK_NAME +: TK_IDENT(name) +: moreTokens ⇒ (name, moreTokens)
    case _ ⇒ throw new IllegalArgumentException(s"Name token missing")
  }
}
