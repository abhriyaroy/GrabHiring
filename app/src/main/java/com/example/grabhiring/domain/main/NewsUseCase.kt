package com.example.grabhiring.domain.main

import com.example.grabhiring.data.Repository
import com.example.grabhiring.domain.model.NewsDomainModel
import io.reactivex.Single

interface NewsUseCase {
  fun getNews(): Single<NewsDomainModel>
}

class NewsInteractor(private val repository: Repository) : NewsUseCase {
  override fun getNews(): Single<NewsDomainModel> {
    return repository.getNews()
  }
}