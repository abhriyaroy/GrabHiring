package com.example.grabhiring.data.model

data class NewsDataModel(
  val status: String,
  val totalResults: Int,
  val articles: ArrayList<ArticleDataModel>
)