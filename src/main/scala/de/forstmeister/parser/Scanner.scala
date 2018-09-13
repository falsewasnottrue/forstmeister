package de.forstmeister.parser

// TODO rename all with TK
sealed trait Token
case object TK_NAME extends Token
case object TK_MODE extends Token
case object SORT extends Token
case object SORTS extends Token
case object TK_SIGNATURE extends Token
case object ORDERING extends Token
case object VARIABLE extends Token
case object VARIABLES extends Token
case object EQUATION extends Token
case object EQUATIONS extends Token
case object CONCLUSION extends Token
case object CONCLUSIONS extends Token
case object COMPLETION extends Token
case object CONFLUENCE extends Token
case object CONVERGENCE extends Token
case object PROOF extends Token
case object REDUCTION extends Token
case object TERMINATION extends Token
case object KBO extends Token
case object LPO extends Token

// TODO Klammern usw.

case class NUMBER(n: Int) extends Token
case class TK_IDENT(text: String) extends Token

object Scanner {
  def scan(text: String): Seq[Token] = text.trim.split("\\s+").toSeq.map {
    case "NAME" ⇒ TK_NAME
    case "MODE" ⇒ TK_MODE

    case t ⇒ TK_IDENT(t)
  }
}
