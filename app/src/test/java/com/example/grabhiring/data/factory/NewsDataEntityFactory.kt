package com.example.grabhiring.data.factory

import com.example.grabhiring.data.model.ArticlesDataEntity
import com.example.grabhiring.data.model.NewsDataEntity
import com.example.grabhiring.data.model.SourceDataEntity
import java.util.*
import java.util.UUID.randomUUID

object NewsDataEntityFactory  {
  fun getModel() = NewsDataEntity(
    randomUUID().toString(),
    Random().nextInt(),
    arrayListOf(
      ArticlesDataEntity(
        SourceDataEntity(
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
      ArticlesDataEntity(
        SourceDataEntity(
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