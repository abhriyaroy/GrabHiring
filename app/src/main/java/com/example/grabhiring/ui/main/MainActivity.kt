package com.example.grabhiring.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grabhiring.R
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.main.MainContract.MainPresenter
import com.example.grabhiring.presenter.main.MainContract.MainView
import com.example.grabhiring.presenter.main.model.NewsPresenterEntity
import com.example.grabhiring.ui.adapter.NewsListAdapter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

  @Inject
  lateinit var presenter: MainPresenter
  @Inject
  lateinit var newListPresenter: NewsListPresenter
  private lateinit var recyclerViewAdapter: NewsListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter.attachView(this)
    initRecyclerView()
  }

  override fun onDestroy() {
    presenter.detachView()
    super.onDestroy()
  }

  override fun getScope(): AndroidLifecycleScopeProvider =
    AndroidLifecycleScopeProvider.from(this.lifecycle, Lifecycle.Event.ON_DESTROY)

  override fun setNewsList(newsPresenterEntity: NewsPresenterEntity) {
    recyclerViewAdapter.setList(newsPresenterEntity.articles)
  }

  private fun initRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    recyclerViewAdapter = NewsListAdapter(newListPresenter)
    recyclerView.adapter = recyclerViewAdapter
  }
}
