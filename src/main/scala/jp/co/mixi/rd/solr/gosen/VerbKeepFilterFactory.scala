package jp.co.mixi.rd.solr.gosen

import org.apache.lucene.analysis.TokenStream

import org.apache.lucene.analysis.util.TokenFilterFactory
import jp.co.mixi.rd.lucene.gosen.VerbKeepFilter


class VerbKeepFilterFactory extends TokenFilterFactory {

  override def create(stream: TokenStream) : TokenStream = {
    new VerbKeepFilter(stream)
  }
}
