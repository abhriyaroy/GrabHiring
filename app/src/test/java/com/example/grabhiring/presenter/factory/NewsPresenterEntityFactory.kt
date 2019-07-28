package com.example.grabhiring.presenter.factory

import com.example.grabhiring.presenter.model.ArticlesPresenterEntity
import com.example.grabhiring.presenter.model.NewsPresenterEntity
import com.example.grabhiring.presenter.model.SourcePresenterEntity
import java.util.*
import java.util.UUID.randomUUID

object NewsPresenterEntityFactory {
  fun getModel() = NewsPresenterEntity(
    randomUUID().toString(),
    Random().nextInt(),
    arrayListOf(
      ArticlesPresenterEntity(
        SourcePresenterEntity(
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
      ArticlesPresenterEntity(
        SourcePresenterEntity(
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