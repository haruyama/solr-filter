package jp.co.mixi.rd.lucene.gosen

import scala.collection.mutable.ArrayBuffer

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.gosen.tokenAttributes.PartOfSpeechAttribute
import org.apache.lucene.analysis.gosen.tokenAttributes.BasicFormAttribute
import org.apache.lucene.analysis.gosen.tokenAttributes.ConjugationAttribute
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute


class VerbKeepFilter(input : TokenStream) extends TokenFilter(input) {

  private val partOfSpeechAtt = addAttribute(classOf[PartOfSpeechAttribute])
  private val basicFormAtt    = addAttribute(classOf[BasicFormAttribute])
  private val termAtt         = addAttribute(classOf[CharTermAttribute])
  private val keywordAtt      = addAttribute(classOf[KeywordAttribute])
  private val conjugationAtt  = addAttribute(classOf[ConjugationAttribute])

  private val verbPrefix        = "動詞-"
  private val sahenPartOfSearch = "名詞-サ変接続"
  private val sahenType         = "サ変・スル"
  private val sahenSuffix       = "する".toArray

  override def incrementToken : Boolean = {
    val termAttBuffer = new ArrayBuffer[Char]
    while (input.incrementToken) {
      val partOfSpeech = partOfSpeechAtt.getPartOfSpeech
      if (partOfSpeech.startsWith(verbPrefix)) {
        if (!keywordAtt.isKeyword) {
          if (conjugationAtt.getConjugationalType == sahenType) {
            termAtt.setEmpty.append(termAttBuffer.toArray)
            termAtt.append(sahenSuffix)
          } else {
            val basicForm = basicFormAtt.getBasicForm
            if (basicForm != "*") {
              termAtt.setEmpty.append(basicForm)
            }
          }
          termAttBuffer.clear
          return true
        }
      } else {
        termAttBuffer.clear
        val buffer = new Array[Char](termAtt.length)
        Array.copy(termAtt.buffer, 0, buffer, 0, termAtt.length)
        termAttBuffer ++= buffer
      }
    }
    false
  }

  override def equals(that : Any) = {
    that match {
      case v : VerbKeepFilter =>
      super.equals(v)
      case _ => false
    }
  }

  override def hashCode() = {
    super.hashCode
  }
}
