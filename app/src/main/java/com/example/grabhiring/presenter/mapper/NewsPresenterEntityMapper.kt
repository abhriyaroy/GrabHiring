package com.example.grabhiring.presenter.mapper

import com.example.grabhiring.domain.model.ArticlesDomainModel
import com.example.grabhiring.domain.model.NewsDomainModel
import com.example.grabhiring.domain.model.SourceDomainModel
import com.example.grabhiring.presenter.model.ArticlesPresenterEntity
import com.example.grabhiring.presenter.model.NewsPresenterEntity
import com.example.grabhiring.presenter.model.SourcePresenterEntity

interface NewsPresenterEntityMapper {
  fun mapToPresenterEntity(newsDomainModel: NewsDomainModel): NewsPresenterEntity
  fun mapFromPresenterEntity(newsPresenterEntity: NewsPresenterEntity): NewsDomainModel
}

class NewsPresenterEntityMapperImpl : NewsPresenterEntityMapper {
  override fun mapToPresenterEntity(newsDomainModel: NewsDomainModel): NewsPresenterEntity {
    return NewsPresenterEntity(
      newsDomainModel.status,
      newsDomainModel.totalResults,
      newsDomainModel.articles.map {
        ArticlesPresenterEntity(
          SourcePresenterEntity(it.source.id, it.source.name),
          it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content
        )
      }.toList() as ArrayList<ArticlesPresenterEntity>
    )
  }

  override fun mapFromPresenterEntity(newsPresenterEntity: NewsPresenterEntity): NewsDomainModel {
    return NewsDomainModel(
      newsPresenterEntity.status,
      newsPresenterEntity.totalResults,
      newsPresenterEntity.articles.map {
        ArticlesDomainModel(
          SourceDomainModel(it.source.id, it.source.name),
          it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content
        )
      }.toList() as ArrayList<ArticlesDomainModel>
    )

  }

}