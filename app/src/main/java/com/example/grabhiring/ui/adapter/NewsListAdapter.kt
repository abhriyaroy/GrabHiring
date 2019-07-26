package com.example.grabhiring.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListView
import com.example.grabhiring.presenter.main.model.ArticlesPresenterEntity

class NewsListAdapter(private val newsListPresenter: NewsListPresenter) :
  Adapter<NewsListViewHolder>() {

  private val articlesList = mutableListOf<ArticlesPresenterEntity>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
    LayoutInflater.from(parent.context).inflate()
  }

  override fun getItemCount(): Int {
    return newsListPresenter.getItemCount(articlesList.toList())
  }

  override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
    newsListPresenter.onBindRepositoryRowViewAtPosition(holder, articlesList, position)
  }

}

class NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), NewsListView {
  override fun showHeadline(headLine: String) {

  }

  override fun showDescription(description: String) {

  }

  override fun showImage(imagePath: String) {

  }

}