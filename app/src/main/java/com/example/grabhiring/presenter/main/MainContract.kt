package com.example.grabhiring.presenter.main

import com.example.grabhiring.presenter.BasePresenter
import com.example.grabhiring.presenter.BaseView
import com.example.grabhiring.presenter.model.NewsPresenterEntity

interface MainContract {

  interface MainView : BaseView {
    fun showProgressLoader()
    fun hideProgressLoader()
    fun setNewsList(newsPresenterEntity: NewsPresenterEntity)
    fun showNewsList()
    fun hideNewsList()
    fun showErrorMessage()
  }

  interface MainPresenter : BasePresenter<MainView> {
    fun decorateView()
    fun handleRefreshClick()
  }
}