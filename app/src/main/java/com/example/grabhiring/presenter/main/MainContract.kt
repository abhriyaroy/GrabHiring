package com.example.grabhiring.presenter.main

import com.example.grabhiring.presenter.BasePresenter
import com.example.grabhiring.presenter.BaseView
import com.example.grabhiring.presenter.main.model.NewsPresenterEntity

interface MainContract {

  interface MainView : BaseView {
    fun setNewsList(newsPresenterEntity: NewsPresenterEntity)
  }

  interface MainPresenter : BasePresenter<MainView> {
    fun decorateView()
  }
}