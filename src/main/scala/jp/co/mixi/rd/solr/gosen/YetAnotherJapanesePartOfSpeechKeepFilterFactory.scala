package jp.co.mixi.rd.solr.gosen

import org.apache.lucene.analysis.TokenStream

import org.apache.solr.analysis.BaseTokenFilterFactory
import org.apache.solr.common.ResourceLoader
import org.apache.solr.util.plugin.ResourceLoaderAware

import jp.co.mixi.rd.lucene.gosen.YetAnotherJapanesePartOfSpeechKeepFilter


class YetAnotherJapanesePartOfSpeechKeepFilterFactory extends BaseTokenFilterFactory with ResourceLoaderAware {
  private var partOfSpeech = ""

  override def inform(loader : ResourceLoader) {
    partOfSpeech = args.get("partOfSpeech")
  }

  override def create(stream: TokenStream) : TokenStream = {
    return new YetAnotherJapanesePartOfSpeechKeepFilter(stream, partOfSpeech)
  }
}
