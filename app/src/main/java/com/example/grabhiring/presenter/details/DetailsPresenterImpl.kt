package com.example.grabhiring.presenter.details

import com.example.grabhiring.R
import com.example.grabhiring.presenter.details.DetailsContract.DetailsPresenter
import com.example.grabhiring.presenter.details.DetailsContract.DetailsView
import com.example.grabhiring.ui.utils.ResourceUtils

class DetailsPresenterImpl(private val resourceUtils: ResourceUtils) : DetailsPresenter {

  private var detailsView: DetailsView? = null

  override fun attachView(view: DetailsView) {
    detailsView = view
  }

  override fun detachView() {
    detailsView = null
  }

  override fun decorateView(heading: String?, url: String?) {
    detailsView?.showNewsHeadline(
      heading ?: resourceUtils.getStringRes(R.string.details_activity_default_headline)
    )
    if (url != null) {
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