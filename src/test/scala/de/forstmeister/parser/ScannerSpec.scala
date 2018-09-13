package de.forstmeister.parser

import org.scalatest.{FlatSpec, Matchers}

class ScannerSpec extends FlatSpec with Matchers {

  "Scanner" should "detect NAME token" in {
    val tokens = Scanner.scan("NAME")
    tokens.size should be(1)
    tokens.head should be(TK_NAME)
  }

  it should "detect NAME token with whitespaces" in {
    val tokens = Scanner.scan("   NAME  ")

    tokens.size should be(1)
    tokens.head should be(TK_NAME)
  }

  it should "detect IDENT tokens" in {
    val tokens = Scanner.scan("group")
    tokens.size should be(1)
    tokens.head should be(TK_IDENT("group"))
  }

  it should "detect MODE token" in {
    val tokens = Scanner.scan("MODE")
    tokens.size should be(1)
    tokens.head should be(TK_MODE)
  }

  it should "treat new lines as white space" in {
    val tokens = Scanner.scan("NAME\ntest")
    tokens should be(Seq(TK_NAME, TK_IDENT("test")))
  }
}
