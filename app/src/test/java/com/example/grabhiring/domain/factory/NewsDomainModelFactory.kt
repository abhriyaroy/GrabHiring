package com.example.grabhiring.domain.factory

import com.example.grabhiring.domain.model.ArticlesDomainModel
import com.example.grabhiring.domain.model.NewsDomainModel
import com.example.grabhiring.domain.model.SourceDomainModel
import java.util.*
import java.util.UUID.randomUUID

object NewsDomainModelFactory{
  fun getModel() = NewsDomainModel(
    randomUUID().toString(),
    Random().nextInt(),
    arrayListOf(
      ArticlesDomainModel(
        SourceDomainModel(
          randomUUID().toString(),
          randomUUID().toString()
        ),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString()
      ),
      ArticlesDomainModel(
        SourceDomainModel(
          randomUUID().toString(),
          randomUUID().toString()
        ),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString(),
        randomUUID().toString()
      )
    )
  )
}