package com.example.grabhiring.data

import com.example.grabhiring.data.api.NewsApiClientFactory
import com.example.grabhiring.data.database.DatabaseHelper
import com.example.grabhiring.data.mapper.NewsDataEntityMapper
import com.example.grabhiring.domain.model.NewsDomainModel
import com.example.grabhiring.exceptions.CacheAbsentException
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
        databaseHelper.clearCache()
        databaseHelper.saveToCache(newsDataEntity)
      }.flatMap {
        databaseHelper.getCachedNews()
      }.onErrorResumeNext {
        if (it is CacheAbsentException) {
          Single.error(it)
        } else {
          databaseHelper.getCachedNews()
        }
      }
      .map {
        mapper.mapFromDataEntity(it)
      }
      .subscribeOn(backgroundScheduler.getIoScheduler())
  }
}