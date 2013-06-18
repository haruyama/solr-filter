package jp.co.mixi.rd.solr.gosen

import org.apache.lucene.analysis.TokenStream

import org.apache.lucene.analysis.util.TokenFilterFactory
import jp.co.mixi.rd.lucene.gosen.YetAnotherJapanesePartOfSpeechKeepFilter
import org.apache.lucene.analysis.util.ResourceLoaderAware
import org.apache.lucene.analysis.util.ResourceLoader


class YetAnotherJapanesePartOfSpeechKeepFilterFactory(args: java.util.Map[String,String]) extends TokenFilterFactory(args) {
  private var partOfSpeech = ""

  def inform(loader: ResourceLoader) {
    partOfSpeech = args.get("partOfSpeech");
  }

  override def create(stream: TokenStream) : TokenStream = {
    new YetAnotherJapanesePartOfSpeechKeepFilter(stream, partOfSpeech)
  }
}
