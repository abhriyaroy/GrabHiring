package com.example.grabhiring.presenter.details

import com.example.grabhiring.presenter.BasePresenter

interface DetailsContract {
  interface DetailsView {
    fun showNewsHeadline(headline: String)
    fun showNewsDetails(url: String)
    fun showDetailsUrlErrorMessage()
  }

  interface DetailsPresenter : BasePresenter<DetailsView> {
    fun decorateView(heading: String?, url: String?)
  }
}