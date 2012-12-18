package jp.co.mixi.rd.lucene.gosen

import java.io.Reader
import java.io.StringReader

import org.apache.lucene.analysis.gosen.GosenTokenizer
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

class VerbKeepFilterSuite extends FilterSuite {

  test("基本1") {
    val reader = new StringReader("私は走っている")
    val stream = new VerbKeepFilter(new GosenTokenizer(reader))

    assertTokenStreamContents(stream, Array("走る", "いる"))
  }

  test("基本2 basicFormが '*' の場合") {
    val reader = new StringReader("はさみを用いる")
    val stream = new VerbKeepFilter(new GosenTokenizer(reader))
    assertTokenStreamContents(stream, Array("用いる"))
  }

  test("サ変1") {
    val reader = new StringReader("相談する")
    val stream = new VerbKeepFilter(new GosenTokenizer(reader))
    assertTokenStreamContents(stream, Array("相談する"))
  }

  test("サ変2") {
    val reader = new StringReader("相談した")
    val stream = new VerbKeepFilter(new GosenTokenizer(reader))
    assertTokenStreamContents(stream, Array("相談する"))
  }

  test("サ変3") {
    val reader = new StringReader("大きくした")
    val stream = new VerbKeepFilter(new GosenTokenizer(reader))
    assertTokenStreamContents(stream, Array("大きくする"))
  }

  test("組合せ1") {
    val reader = new StringReader("相談したいことがあるのですが")
    val stream = new VerbKeepFilter(new GosenTokenizer(reader))
    assertTokenStreamContents(stream, Array("相談する", "ある"))
  }
}
