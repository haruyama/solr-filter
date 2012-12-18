package jp.co.mixi.rd.lucene.gosen

import java.io.Reader
import java.io.StringReader

import org.apache.lucene.analysis.gosen.GosenTokenizer
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

class YetAnotherJapanesePartOfSpeechKeepFilterSuite extends FilterSuite {

  test("動詞1") {
    val reader = new StringReader("私は走っている")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new GosenTokenizer(reader), "動詞")

    assertTokenStreamContents(stream, Array("走る", "いる"))
  }

  test("動詞2 basicFormが '*' の場合") {
    val reader = new StringReader("はさみを用いる")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new GosenTokenizer(reader), "動詞")
    assertTokenStreamContents(stream, Array("用いる"))
  }

  test("サ変動詞1") {
    val reader = new StringReader("相談する")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new GosenTokenizer(reader), "動詞")
    assertTokenStreamContents(stream, Array("する"))
  }

  test("サ変動詞2") {
    val reader = new StringReader("相談した")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new GosenTokenizer(reader), "動詞")
    assertTokenStreamContents(stream, Array("する"))
  }

  test("形容詞1") {
    val reader = new StringReader("かわいい服")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new GosenTokenizer(reader), "形容詞")
    assertTokenStreamContents(stream, Array("かわいい"))
  }

  test("形容詞2") {
    val reader = new StringReader("かわいく撮れた")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new GosenTokenizer(reader), "形容詞")
    assertTokenStreamContents(stream, Array("かわいい"))
  }

  test("名詞1") {
    val reader = new StringReader("かわいい服")
    val stream = new YetAnotherJapanesePartOfSpeechKeepFilter(new GosenTokenizer(reader), "名詞")
    assertTokenStreamContents(stream, Array("服"))
  }
}
