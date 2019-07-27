package com.example.grabhiring.ui.details

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebSettings.LOAD_DEFAULT
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.grabhiring.R
import com.example.grabhiring.presenter.details.DetailsContract.DetailsPresenter
import com.example.grabhiring.presenter.details.DetailsContract.DetailsView
import com.example.grabhiring.ui.adapter.NEWS_HEADING
import com.example.grabhiring.ui.adapter.NEWS_URL
import com.example.grabhiring.ui.utils.gone
import com.example.grabhiring.ui.utils.showToast
import com.example.grabhiring.ui.utils.visible
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject


class DetailsActivity : AppCompatActivity(), DetailsView {

  @Inject
  internal lateinit var detailsPresenter: DetailsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    overridePendingTransition(R.anim.slide_from_right, R.anim.no_change)
    setContentView(R.layout.activity_details)
    detailsPresenter.attachView(this)
    initToolbar()
    initArguments()
    initWebView()
  }

  override fun onDestroy() {
    detailsPresenter.detachView()
    super.onDestroy()
  }

  override fun onBackPressed() {
    overridePendingTransition(R.anim.no_change, R.anim.slide_to_right)
    super.onBackPressed()
  }

  override fun showNewsHeadline(headline: String) {
    supportActionBar?.title = headline
  }

  override fun showNewsDetails(url: String) {
    webView.loadUrl(url)
  }

  override fun showDetailsUrlErrorMessage() {
    showToast("Couldn't load news details")
  }

  override fun showWaitLoader() {
    progressCircle.visible()
  }

  override fun hideWaitLoader() {
    progressCircle.gone()
  }

  override fun exitView() {
    onBackPressed()
  }

  private fun initToolbar() {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    toolbar.setNavigationOnClickListener {
      onBackPressed()
    }
  }

  private fun initArguments() {
    detailsPresenter.decorateView(
      intent.getStringExtra(NEWS_HEADING),
      intent.getStringExtra(NEWS_URL)
    )
  }

  private fun initWebView() {
    webView.webViewClient = object : WebViewClient() {
      override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        detailsPresenter.notifyNewsDetailsLoadingStarted()
      }

      override fun onPageFinished(view: WebView, url: String) {
        detailsPresenter.notifyNewsDetailsLoadingFinished()
      }
    }
    with(webView.settings) {
      javaScriptEnabled = true
      setAppCacheEnabled(true)
      setAppCachePath(cacheDir.path)
      cacheMode = LOAD_DEFAULT
    }
  }
}
