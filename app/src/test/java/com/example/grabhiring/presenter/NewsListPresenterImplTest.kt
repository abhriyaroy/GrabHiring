package com.example.grabhiring.presenter

import com.example.grabhiring.presenter.adapter.NewsListContract.NewsListView
import com.example.grabhiring.presenter.adapter.NewsListPresenterImpl
import com.example.grabhiring.presenter.factory.NewsPresenterEntityFactory
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsListPresenterImplTest {

  @Mock
  private lateinit var newsListView: NewsListView
  private lateinit var newsListPresenter: NewsListPresenterImpl
  private val newsPresenterEntity = NewsPresenterEntityFactory.getModel()

  @Before
  fun setup() {
    newsListPresenter = NewsListPresenterImpl()
  }

  @Test
  fun `should return list size on getItemCount call success`() {
    assertEquals(
      newsPresenterEntity.articles.size,
      newsListPresenter.getItemCount(newsPresenterEntity.articles)
    )
  }

  @Test
  fun `should show news on onBindRepositoryRowViewAtPosition call success`() {
    newsListPresenter.getItemCount(newsPresenterEntity.articles)
    newsPresenterEntity.articles.forEachIndexed { index, articlesPresenterEntity ->
      newsListPresenter.onBindRepositoryRowViewAtPosition(
        newsListView,
        newsPresenterEntity.articles,
        index
      )
      verify(newsListView).showHeadline(articlesPresenterEntity.title)
      verify(newsListView).showDescription(articlesPresenterEntity.description)
      verify(newsListView).showImage(articlesPresenterEntity.urlToImage)
    }
    verify(newsListView, times(newsPresenterEntity.articles.size)).attachClickListener()
  }

  @Test
  fun `should show news details on handleItemClick call success`() {
    newsListPresenter.getItemCount(newsPresenterEntity.articles)
    newsPresenterEntity.articles.forEachIndexed { index, articlesPresenterEntity ->
      newsListPresenter.handleItemClick(
        newsListView,
        index
      )
      verify(newsListView).showNewsDetails(
        articlesPresenterEntity.title,
        articlesPresenterEntity.url
      )
    }
  }

  @After
  fun tearDown() {
    verifyNoMoreInteractions(newsListView)
  }
}