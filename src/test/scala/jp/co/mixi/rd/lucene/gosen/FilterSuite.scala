package jp.co.mixi.rd.lucene.gosen

import org.scalatest.FunSuite

import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute

trait FilterSuite extends FunSuite {

  def assertTokenStreamContents(ts: TokenStream, expected: Array[String]) {
    val termAtt = ts.getAttribute(classOf[CharTermAttribute])
      expected.foreach {
      term =>
      assert(ts.incrementToken)
      assert(term === termAtt.toString)
    }
    assert(!ts.incrementToken)
  }

}
