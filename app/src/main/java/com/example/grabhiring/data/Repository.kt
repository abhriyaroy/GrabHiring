package com.example.grabhiring.data

import com.example.grabhiring.data.api.NewsApiClientFactory
import com.example.grabhiring.data.database.DatabaseHelper
import com.example.grabhiring.data.mapper.NewsDataEntityMapper
import com.example.grabhiring.domain.model.NewsDomainModel
import io.reactivex.Single

interface Repository {
  fun getNews(): Single<NewsDomainModel>
}

class RepositoryImpl(
  private val newsApiClientFactory: NewsApiClientFactory,
  private val databaseHelper: DatabaseHelper,
  private val mapper: NewsDataEntityMapper,
  private val backgroundScheduler: BackgroundScheduler
) : Repository {

  override fun getNews(): Single<NewsDomainModel> {
    return newsApiClientFactory.getNews()
      .map { newsDataEntity ->
        databaseHelper.getNewsDao().let {
          it.deleteAllCache()
          it.saveCache(newsDataEntity)
        }
      }.flatMap {
        databaseHelper.getNewsDao().newsDao()
          .getCache()
      }
      .map {
        mapper.mapFromDataEntity(it[0].data)
      }
      .subscribeOn(backgroundScheduler.getIoScheduler())
  }
}