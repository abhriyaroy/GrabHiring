package com.example.grabhiring.presenter.details

import com.example.grabhiring.presenter.BasePresenter

interface DetailsContract {
  interface DetailsView {
    fun showNewsHeadline(headline: String)
    fun showNewsDetails(url: String)
    fun showDetailsUrlErrorMessage()
    fun showWaitLoader()
    fun hideWaitLoader()
    fun exitView()
  }

  interface DetailsPresenter : BasePresenter<DetailsView> {
    fun decorateView(heading: String?, url: String?)
    fun notifyNewsDetailsLoadingStarted()
    fun notifyNewsDetailsLoadingFinished()
  }
}