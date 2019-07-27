package com.example.grabhiring.data.model

data class NewsDataEntity(
  val status: String?,
  val totalResults: Int?,
  val articles: ArrayList<ArticlesDataEntity>
)