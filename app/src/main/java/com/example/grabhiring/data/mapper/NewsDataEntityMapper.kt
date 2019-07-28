package com.example.grabhiring.data.mapper

import com.example.grabhiring.data.model.ArticlesDataEntity
import com.example.grabhiring.data.model.NewsDataEntity
import com.example.grabhiring.data.model.SourceDataEntity
import com.example.grabhiring.domain.model.ArticlesDomainModel
import com.example.grabhiring.domain.model.NewsDomainModel
import com.example.grabhiring.domain.model.SourceDomainModel

interface NewsDataEntityMapper {
  fun mapToDataEntity(newsDomainModel: NewsDomainModel): NewsDataEntity
  fun mapFromDataEntity(newsDataEntity: NewsDataEntity): NewsDomainModel
}

class NewsDataEntityMapperImpl : NewsDataEntityMapper {

  override fun mapToDataEntity(newsDomainModel: NewsDomainModel): NewsDataEntity {
    return NewsDataEntity(
      newsDomainModel.status,
      newsDomainModel.totalResults,
      newsDomainModel.articles.map {
        ArticlesDataEntity(
          SourceDataEntity(it.source.id, it.source.name),
          it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content
        )
      }.toMutableList() as ArrayList<ArticlesDataEntity>
    )
  }

  override fun mapFromDataEntity(newsDataEntity: NewsDataEntity): NewsDomainModel {
    return NewsDomainModel(
      newsDataEntity.status,
      newsDataEntity.totalResults,
      newsDataEntity.articles.map {
        ArticlesDomainModel(
          SourceDomainModel(it.source.id, it.source.name),
          it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content
        )
      }.toMutableList() as ArrayList<ArticlesDomainModel>
    )
  }
}