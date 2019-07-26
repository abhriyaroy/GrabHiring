package com.example.grabhiring.presenter.main

import com.example.grabhiring.domain.main.NewsUseCase
import com.example.grabhiring.presenter.main.MainContract.MainPreseter
import com.example.grabhiring.presenter.main.MainContract.MainView

class MainPresenterImpl(newsUseCase: NewsUseCase) : MainPreseter {

  private var mainView: MainView? = null

  override fun attachView(view: MainView) {
    mainView = view
  }

  override fun detachView() {
    mainView = null
  }

  override fun decorateView() {

  }
}