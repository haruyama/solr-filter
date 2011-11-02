package jp.co.mixi.rd.solr.gosen

import org.apache.lucene.analysis.TokenStream

import org.apache.solr.analysis.BaseTokenFilterFactory
import org.apache.solr.common.ResourceLoader
import org.apache.solr.util.plugin.ResourceLoaderAware

import jp.co.mixi.rd.lucene.gosen.VerbKeepFilter


class VerbKeepFilterFactory extends BaseTokenFilterFactory with ResourceLoaderAware {

  override def inform(loader : ResourceLoader) {
  }

  override def create(stream: TokenStream) : TokenStream = {
    new VerbKeepFilter(stream)
  }
}
