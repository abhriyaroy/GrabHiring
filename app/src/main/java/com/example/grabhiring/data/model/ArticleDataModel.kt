package com.example.grabhiring.data.model

data class ArticleDataModel(
  val source: SourceDataModel,
  val author: String,
  val title: String,
  val description: String,
  val url: String,
  val urlToImage: String,
  val publishedAt: String,
  val content: String
)