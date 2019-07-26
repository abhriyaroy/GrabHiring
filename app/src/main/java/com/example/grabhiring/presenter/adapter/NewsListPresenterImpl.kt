package com.example.grabhiring.presenter.adapter

import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.main.model.ArticlesPresenterEntity
import com.example.grabhiring.presenter.main.model.NewsPresenterEntity

class NewsListPresenterImpl : NewsListPresenter {

  override fun onBindRepositoryRowViewAtPosition(
    newsListView: NewsListContract.NewsListView,
    articlesList: List<ArticlesPresenterEntity>,
    position: Int
  ) {

  }

  override fun getItemCount(list: List<NewsPresenterEntity>): Int {
    return list.size
  }

}
