package com.example.grabhiring.presenter.main.model

data class NewsPresenterEntity(
  val status: String,
  val totalResults: Int,
  val articles: ArrayList<ArticlesPresenterEntity>
)