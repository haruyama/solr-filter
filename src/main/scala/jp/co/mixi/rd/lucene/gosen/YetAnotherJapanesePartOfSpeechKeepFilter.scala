package jp.co.mixi.rd.lucene.gosen

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.gosen.tokenAttributes.PartOfSpeechAttribute
import org.apache.lucene.analysis.gosen.tokenAttributes.BasicFormAttribute
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute


class YetAnotherJapanesePartOfSpeechKeepFilter(input : TokenStream,
  partOfSpeech : String) extends TokenFilter(input) {

  private val partOfSpeechAtt = addAttribute(classOf[PartOfSpeechAttribute])
  private val basicFormAtt    = addAttribute(classOf[BasicFormAttribute])
  private val termAtt         = addAttribute(classOf[CharTermAttribute])
  private val keywordAtt      = addAttribute(classOf[KeywordAttribute])
  private val prefix          = partOfSpeech + "-"

  override def incrementToken : Boolean = {
    while (input.incrementToken) {
      if (partOfSpeechAtt.getPartOfSpeech.startsWith(prefix)) {
        if (!keywordAtt.isKeyword) {
          val basicForm = basicFormAtt.getBasicForm
          if (basicForm != "*") {
            termAtt.setEmpty.append(basicForm)
          }
          return true
        }
      }
    }
    false
  }

  override def equals(that : Any) = {
    that match {
      case y : YetAnotherJapanesePartOfSpeechKeepFilter =>
      super.equals(y) && y.prefix == prefix
      case _ => false
    }
  }

  override def hashCode() = {
    super.hashCode * 31 + prefix.hashCode
  }
}
