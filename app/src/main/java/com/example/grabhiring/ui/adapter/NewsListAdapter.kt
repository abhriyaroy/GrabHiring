package com.example.grabhiring.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.grabhiring.R
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListView
import com.example.grabhiring.presenter.main.model.ArticlesPresenterEntity
import kotlinx.android.synthetic.main.item_news_list.view.*

class NewsListAdapter(private val newsListPresenter: NewsListPresenter) :
  Adapter<NewsListViewHolder>() {

  private var articlesList = listOf<ArticlesPresenterEntity>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
    return NewsListViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.item_news_list,
        parent,
        false
      )
    )
  }

  override fun getItemCount(): Int {
    return newsListPresenter.getItemCount(articlesList)
  }

  override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
    newsListPresenter.onBindRepositoryRowViewAtPosition(holder, articlesList, position)
  }

  fun setList(list: List<ArticlesPresenterEntity>) {
    articlesList = list
    notifyDataSetChanged()
  }

}

class NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), NewsListView {
  override fun showHeadline(headLine: String) {
    itemView.headline.text = headLine
  }

  override fun showDescription(description: String) {
    itemView.description.text = description
  }

  override fun showImage(imagePath: String) {

  }

}