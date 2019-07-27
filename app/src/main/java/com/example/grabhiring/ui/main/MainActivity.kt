package com.example.grabhiring.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grabhiring.R
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.main.MainContract.MainPresenter
import com.example.grabhiring.presenter.main.MainContract.MainView
import com.example.grabhiring.presenter.model.NewsPresenterEntity
import com.example.grabhiring.ui.adapter.NewsListAdapter
import com.example.grabhiring.ui.imageloader.ImageLoader
import com.example.grabhiring.ui.utils.gone
import com.example.grabhiring.ui.utils.showToast
import com.example.grabhiring.ui.utils.visible
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

  @Inject
  lateinit var presenter: MainPresenter
  @Inject
  lateinit var newListPresenter: NewsListPresenter
  @Inject
  lateinit var imageLoader: ImageLoader
  private lateinit var recyclerViewAdapter: NewsListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter.attachView(this)
    initToolbar()
    initRecyclerView()
    presenter.decorateView()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.refresh_menu, menu)
    return true
  }

  override fun onDestroy() {
    presenter.detachView()
    super.onDestroy()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.refreshNews -> {
        presenter.handleRefreshClick()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun getScope(): AndroidLifecycleScopeProvider =
    AndroidLifecycleScopeProvider.from(this.lifecycle, ON_DESTROY)

  override fun hideProgressLoader() {
    progressCircle.gone()
  }

  override fun showProgressLoader() {
    progressCircle.visible()
  }

  override fun setNewsList(newsPresenterEntity: NewsPresenterEntity) {
    recyclerViewAdapter.setList(newsPresenterEntity.articles)
    recyclerView.scheduleLayoutAnimation()
  }

  override fun showNewsList() {
    recyclerView.visible()
  }

  override fun hideNewsList() {
    recyclerView.gone()
  }

  override fun showErrorMessage() {
    showToast("Ooops....something is not right!")
  }

  private fun initToolbar() {
    toolbar.title = "Breaking News"
    setSupportActionBar(toolbar)
  }

  private fun initRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    recyclerViewAdapter = NewsListAdapter(this, imageLoader, newListPresenter)
    recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(
      this, R.anim.recycler_view_layout_animation
    )
    recyclerView.adapter = recyclerViewAdapter
  }
}
