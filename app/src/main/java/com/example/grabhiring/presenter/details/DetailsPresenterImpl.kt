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

  override fun decorateView(heading: String, url: String) {

  }
}