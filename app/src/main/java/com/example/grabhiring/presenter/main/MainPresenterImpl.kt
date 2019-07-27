package com.example.grabhiring.presenter.main

import com.example.grabhiring.domain.main.NewsUseCase
import com.example.grabhiring.presenter.main.MainContract.MainPresenter
import com.example.grabhiring.presenter.main.MainContract.MainView
import com.example.grabhiring.presenter.mapper.NewsPresenterEntityMapper
import com.example.grabhiring.ui.MainScheduler

class MainPresenterImpl(
  private val newsUseCase: NewsUseCase,
  private val newsPresenterEntityMapper: NewsPresenterEntityMapper,
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
      .map {
        newsPresenterEntityMapper.mapToPresenterEntity(it)
      }
      .observeOn(mainScheduler.getMainScheduler())
      //.autoDisposable(mainView!!.getScope())
      .doOnSubscribe {
        mainView?.showProgressLoader()
      }
      .subscribe({
        mainView?.hideProgressLoader()
        mainView?.setNewsList(it)
      }, {
        println(it.message)
        mainView?.hideProgressLoader()
      })
  }
}