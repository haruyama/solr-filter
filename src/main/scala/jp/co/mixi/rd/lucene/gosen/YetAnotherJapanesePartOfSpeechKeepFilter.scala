package jp.co.mixi.rd.lucene.gosen

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.ja.tokenAttributes.PartOfSpeechAttribute
import org.apache.lucene.analysis.ja.tokenAttributes.BasicFormAttribute
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute


class YetAnotherJapanesePartOfSpeechKeepFilter(input : TokenStream,
  partOfSpeech : String) extends TokenFilter(input) {

  val partOfSpeechAtt = addAttribute(classOf[PartOfSpeechAttribute])
  val basicFormAtt    = addAttribute(classOf[BasicFormAttribute])
  val termAtt         = addAttribute(classOf[CharTermAttribute])
  val keywordAtt      = addAttribute(classOf[KeywordAttribute])
  val prefix          = partOfSpeech + "-"

  override def incrementToken : Boolean = {
    while (input.incrementToken) {
      if (partOfSpeechAtt.getPartOfSpeech.startsWith(prefix)) {
        if (!keywordAtt.isKeyword) {
          val basicForm = basicFormAtt.getBasicForm
          if (basicForm != null && basicForm != "*") {
            termAtt.setEmpty.append(basicForm)
          }
          return true
        }
      }
    }
    false
  }
}
