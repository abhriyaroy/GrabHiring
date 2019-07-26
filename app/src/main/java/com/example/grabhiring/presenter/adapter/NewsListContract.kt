package com.example.grabhiring.presenter.adapter

import com.example.grabhiring.presenter.model.ArticlesPresenterEntity

interface NewsListContract {

  interface NewsListView {
    fun showHeadline(headLine : String)
    fun showDescription(description : String)
    fun showImage(imagePath : String)
  }

  interface NewsListPresenter {
    fun onBindRepositoryRowViewAtPosition(
      newsListView: NewsListView,
      articlesList: List<ArticlesPresenterEntity>,
      position: Int
    )
    fun getItemCount(list: List<ArticlesPresenterEntity>): Int
  }

}