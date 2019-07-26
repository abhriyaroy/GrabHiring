package com.example.grabhiring.presenter.adapter

import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListView
import com.example.grabhiring.presenter.model.ArticlesPresenterEntity

class NewsListPresenterImpl : NewsListPresenter {

  override fun getItemCount(list: List<ArticlesPresenterEntity>): Int {
    return list.size
  }

  override fun onBindRepositoryRowViewAtPosition(
    newsListView: NewsListView,
    articlesList: List<ArticlesPresenterEntity>,
    position: Int
  ) {
    with(articlesList[position]) {
      newsListView.showHeadline(title)
      newsListView.showDescription(description)
      newsListView.showDescription(urlToImage)
    }
  }

}
