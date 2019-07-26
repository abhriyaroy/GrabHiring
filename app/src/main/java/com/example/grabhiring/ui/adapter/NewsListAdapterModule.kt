package com.example.grabhiring.ui.adapter

import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListPresenter
import com.example.grabhiring.presenter.adapter.NewsListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class NewsListAdapterModule {

  @PerActivity
  @Provides
  fun providesNewsListPresenter(): NewsListPresenter = NewsListPresenterImpl()
}