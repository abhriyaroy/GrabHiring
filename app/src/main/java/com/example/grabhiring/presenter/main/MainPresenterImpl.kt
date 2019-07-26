package com.example.grabhiring.presenter.main

import com.example.grabhiring.domain.main.NewsUseCase
import com.example.grabhiring.presenter.main.MainContract.MainPresenter
import com.example.grabhiring.presenter.main.MainContract.MainView
import com.example.grabhiring.ui.MainScheduler

class MainPresenterImpl(
  private val newsUseCase: NewsUseCase,
  private val mainScheduler: MainScheduler
) : MainPresenter {

  private var mainView: MainView? = null

  override fun attachView(view: MainView) {
    mainView = view
  }

  override fun detachView() {
    mainView = null
  }

  override fun decorateView() {
    newsUseCase.getNews()
  }
}