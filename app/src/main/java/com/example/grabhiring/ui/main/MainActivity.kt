package com.example.grabhiring.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.grabhiring.R
import com.example.grabhiring.presenter.main.MainContract.MainPresenter
import com.example.grabhiring.presenter.main.MainContract.MainView
import com.example.grabhiring.presenter.main.model.NewsPresenterEntity
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

  @Inject
  lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun getScope(): AndroidLifecycleScopeProvider =
    AndroidLifecycleScopeProvider.from(this.lifecycle, Lifecycle.Event.ON_DESTROY)

  override fun setNewsList(newsPresenterEntity: NewsPresenterEntity) {

  }
}
