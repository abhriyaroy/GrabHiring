package com.example.grabhiring.domain.model

data class NewsDomainModel(
  val status: String,
  val totalResults: Int,
  val articles: ArrayList<ArticlesDomainModel>
)