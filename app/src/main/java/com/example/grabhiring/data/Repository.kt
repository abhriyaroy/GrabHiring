package com.example.grabhiring.data

import com.example.grabhiring.domain.model.NewsDomainModel
import io.reactivex.Single

interface Repository {
  fun getNews(): Single<List<NewsDomainModel>>
}

class RepositoryImpl : Repository {

  override fun getNews(): Single<List<NewsDomainModel>> {

  }
}