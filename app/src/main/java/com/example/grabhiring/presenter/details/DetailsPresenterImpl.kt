package com.example.grabhiring.presenter.details

import com.example.grabhiring.presenter.details.DetailsContract.DetailsPresenter
import com.example.grabhiring.presenter.details.DetailsContract.DetailsView

class DetailsPresenterImpl : DetailsPresenter {

  private var detailsView: DetailsView? = null

  override fun attachView(view: DetailsView) {
    detailsView = view
  }

  override fun detachView() {
    detailsView = null
  }

  override fun decorateView(heading: String?, url: String?) {
    detailsView?.showNewsHeadline(heading?:"News Details")
    if (url!=null){
      detailsView?.showNewsDetails(url)
    } else {
      detailsView?.showDetailsUrlErrorMessage()
      detailsView?.exitView()
    }
  }

  override fun notifyNewsDetailsLoadingStarted() {
    detailsView?.showWaitLoader()
  }

  override fun notifyNewsDetailsLoadingFinished() {
    detailsView?.hideWaitLoader()
  }
}