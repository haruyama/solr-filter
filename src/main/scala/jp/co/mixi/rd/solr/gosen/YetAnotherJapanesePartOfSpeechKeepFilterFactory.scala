package jp.co.mixi.rd.solr.gosen

import org.apache.lucene.analysis.TokenStream

import org.apache.lucene.analysis.util.TokenFilterFactory
import jp.co.mixi.rd.lucene.gosen.YetAnotherJapanesePartOfSpeechKeepFilter


class YetAnotherJapanesePartOfSpeechKeepFilterFactory extends TokenFilterFactory {
  private var partOfSpeech = ""

  override def create(stream: TokenStream) : TokenStream = {
    new YetAnotherJapanesePartOfSpeechKeepFilter(stream, partOfSpeech)
  }
}
