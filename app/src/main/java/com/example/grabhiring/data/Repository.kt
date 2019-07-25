package com.example.grabhiring.data

import com.example.grabhiring.data.mapper.NewsDataEntityMapper
import com.example.grabhiring.domain.model.NewsDomainModel
import io.reactivex.Single

interface Repository {
  fun getNews(): Single<List<NewsDomainModel>>
}

class RepositoryImpl(mapper: NewsDataEntityMapper) : Repository {

  override fun getNews(): Single<List<NewsDomainModel>> {
    
  }
}