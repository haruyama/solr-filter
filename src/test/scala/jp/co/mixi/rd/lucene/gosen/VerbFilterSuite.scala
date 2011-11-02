package jp.co.mixi.rd.lucene.gosen

import java.io.Reader
import java.io.StringReader

import org.apache.lucene.analysis.ja.JapaneseTokenizer
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

class VerbFilterSuite extends FilterSuite {

  test("基本1") {
    val reader = new StringReader("私は走っている")
    val stream = new VerbFilter(new JapaneseTokenizer(reader))

    assertTokenStreamContents(stream, Array("走る", "いる"))
  }

  test("基本2 basicFormが '*' の場合") {
    val reader = new StringReader("はさみを用いる")
    val stream = new VerbFilter(new JapaneseTokenizer(reader))
    assertTokenStreamContents(stream, Array("用いる"))
  }

  test("サ変1") {
    val reader = new StringReader("相談する")
    val stream = new VerbFilter(new JapaneseTokenizer(reader))
    assertTokenStreamContents(stream, Array("相談する"))
  }

  test("サ変2") {
    val reader = new StringReader("相談した")
    val stream = new VerbFilter(new JapaneseTokenizer(reader))
    assertTokenStreamContents(stream, Array("相談する"))
  }

  test("組合せ1") {
    val reader = new StringReader("相談したいことがあるのですが")
    val stream = new VerbFilter(new JapaneseTokenizer(reader))
    assertTokenStreamContents(stream, Array("相談する", "ある"))
  }
}
