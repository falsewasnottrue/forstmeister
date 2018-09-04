package de.forstmeister.domain

class Term(val topSymbol: Symbol, val subterms: Seq[Term]) {

  def isVariable: Boolean = topSymbol.isVariable

  // TODO getAllSubterms: gibt eine Aufzaehlung aller Subterme aus (incl. sich selber)
  // TODO getAllSymbols: Liefert eine Aufzaehlung aller Symbole zurueck;

  // TODO containsVariable: Prueft, ob Variable im Term vorkommt.
  // TODO replaceWith: Ersetzt den Term mit einem anderen Term t, indem topSymbol und Subterme von t uebernommen werden

  override def equals(obj: scala.Any): Boolean = {
    if (!obj.isInstanceOf[Term]) {
      return false
    } else {
      val other = obj.asInstanceOf[Term]
      other.topSymbol == this.topSymbol && subterms.zip(other.subterms).forall {
        case (term1, term2) ⇒ term1 == term2
      }
    }
  }

  override def toString: String = topSymbol.toString + (if (subterms.isEmpty) "" else "(" + subterms.mkString(",") + ")")
}

object Term {
  def apply(variableSymbol: VariableSymbol) = new Term(variableSymbol, Nil)
  def apply(functionSymbol: FunctionSymbol, subterms: Seq[Term]) = {
    if (functionSymbol.arity != subterms.size) {
      throw new IllegalArgumentException(s"Number of subterms ${subterms.size} does not equal function arity ${functionSymbol.arity}")
    }
    new Term(functionSymbol, subterms)
  }
  def apply(functionSymbol: FunctionSymbol) = {
    if (functionSymbol.arity > 0) {
      throw new IllegalArgumentException(s"Function symbol is not a constant")
    }
    new Term(functionSymbol, Nil)
  }
  // TODO copy constructor
}
