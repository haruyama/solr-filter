package jp.co.mixi.rd.lucene.gosen

import org.apache.lucene.analysis.TokenFilter
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.ja.tokenAttributes.PartOfSpeechAttribute
import org.apache.lucene.analysis.ja.tokenAttributes.BasicFormAttribute
import org.apache.lucene.analysis.ja.tokenAttributes.ConjugationAttribute
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
  private val sahenSuffix       = "する"

  override def incrementToken : Boolean = {
    while (input.incrementToken) {
      val partOfSpeech = partOfSpeechAtt.getPartOfSpeech
      if (partOfSpeech.startsWith(verbPrefix)) {
        if (conjugationAtt.getConjugationalType != sahenType) {
          if (!keywordAtt.isKeyword) {
            val basicForm = basicFormAtt.getBasicForm
            if (basicForm != "*") {
              termAtt.setEmpty.append(basicForm)
            }
            return true
          }
        }
      } else if (partOfSpeech == sahenPartOfSearch) {
        termAtt.append(sahenSuffix)
        return true
      }
    }
    false
  }
}
