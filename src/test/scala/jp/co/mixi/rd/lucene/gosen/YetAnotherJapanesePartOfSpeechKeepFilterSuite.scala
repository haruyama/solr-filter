package jp.co.mixi.rd.lucene.gosen

import java.io.Reader
import java.io.StringReader

import org.apache.lucene.analysis.ja.JapaneseTokenizer
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

import org.scalatest.FunSuite

class YetAnotherJapanesePartOfSpeechKeepFilterSuite extends FunSuite {

  def assertTokenStreamContents(ts: TokenStream, expected: Array[String]) {
    val termAtt = ts.getAttribute(classOf[CharTermAttribute])
      expected.foreach {
      term =>
      assert(ts.incrementToken)
      assert(term === termAtt.toString)
    }
  }

  test("動詞1") {
    val reader = new StringReader("私は走っている")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new JapaneseTokenizer(reader), "動詞")
    assertTokenStreamContents(stream, Array("走る"))
  }

  test("動詞2 - basicFormが '*' の場合") {
    val reader = new StringReader("はさみを用いる")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new JapaneseTokenizer(reader), "動詞")
    assertTokenStreamContents(stream, Array("用いる"))
  }
}
