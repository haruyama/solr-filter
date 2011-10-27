package jp.co.mixi.rd.lucene.gosen

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.ja.tokenAttributes.PartOfSpeechAttribute
import org.apache.lucene.analysis.ja.tokenAttributes.BasicFormAttribute
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute


class YetAnotherJapanesePartOfSpeachKeepFilter(input : TokenStream, partOfSpeech : String) extends TokenFilter(input) {
  val partOfSpeechAtt = addAttribute(classOf[PartOfSpeechAttribute])
  val basicFormAtt = addAttribute(classOf[BasicFormAttribute])
  val termAtt = addAttribute(classOf[CharTermAttribute])
  val prefix = partOfSpeech + "-"

  override def incrementToken : Boolean = {
    while (input.incrementToken) {
      if (partOfSpeechAtt.getPartOfSpeech().startsWith(prefix)) {
        val basicForm = basicFormAtt.getBasicForm
        if (basicForm != "*") {
          termAtt.setEmpty()
          termAtt.copyBuffer(basicForm.toCharArray, 0, basicForm.size)
        }
        return true
        }
      }
      return false
    }
  }
