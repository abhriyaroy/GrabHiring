package com.example.grabhiring.presenter

import com.example.grabhiring.R
import com.example.grabhiring.presenter.details.DetailsContract.DetailsView
import com.example.grabhiring.presenter.details.DetailsPresenterImpl
import com.example.grabhiring.ui.utils.ResourceUtils
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.UUID.*

@RunWith(MockitoJUnitRunner::class)
class DetailsPresenterImplTest {

  @Mock
  private lateinit var resourceUtils: ResourceUtils
  @Mock
  private lateinit var detailsView: DetailsView
  private lateinit var detailsPresenterImpl: DetailsPresenterImpl
  private val headline = randomUUID().toString()
  private val url = randomUUID().toString()

  @Before
  fun setup() {
    detailsPresenterImpl = DetailsPresenterImpl(resourceUtils)

    detailsPresenterImpl.attachView(detailsView)
  }

  @Test
  fun `should show news headline and details on decorateView call success`(){
    detailsPresenterImpl.decorateView(headline, url)

    verify(detailsView).showNewsHeadline(headline)
    verify(detailsView).showNewsDetails(url)
  }

  @Test
  fun `should show default news headline and details on decorateView call success`(){
    val defaultHeadline = randomUUID().toString()
    `when`(resourceUtils.getStringRes(R.string.details_activity_default_headline)).thenReturn(defaultHeadline)
    detailsPresenterImpl.decorateView(null, url)

    verify(detailsView).showNewsHeadline(defaultHeadline)
    verify(detailsView).showNewsDetails(url)
  }

  @Test
  fun `should exit view on decorateView call failure due to details url having null value`(){
    detailsPresenterImpl.decorateView(headline, null)

    verify(detailsView).showNewsHeadline(headline)
    verify(detailsView).showDetailsUrlErrorMessage()
    verify(detailsView).exitView()
  }

  @Test fun `should show wait loader on notifyNewsDetailsLoadingStarted call success`(){
    detailsPresenterImpl.notifyNewsDetailsLoadingStarted()

    verify(detailsView).showWaitLoader()
  }

  @Test fun `should hide wait loader on notifyNewsDetailsLoadingFinished call success`(){
    detailsPresenterImpl.notifyNewsDetailsLoadingFinished()

    verify(detailsView).hideWaitLoader()
  }

  @After
  fun tearDown() {
    verifyNoMoreInteractions(detailsView)
    detailsPresenterImpl.detachView()
  }

}