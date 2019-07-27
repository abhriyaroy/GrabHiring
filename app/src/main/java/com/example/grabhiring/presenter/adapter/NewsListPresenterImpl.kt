package com.example.grabhiring.presenter.adapter

import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListView
import com.example.grabhiring.presenter.model.ArticlesPresenterEntity

class NewsListPresenterImpl : NewsListPresenter {

  private var list = listOf<ArticlesPresenterEntity>()

  override fun getItemCount(list: List<ArticlesPresenterEntity>): Int {
    this.list = list
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
      newsListView.showImage(urlToImage)
    }
    newsListView.attachClickListener()
  }

  override fun handleItemClick(newsListView: NewsListView, position: Int) {
    with(list[position]) {
      newsListView.expandImage(title, url)
    }
  }

}
