package com.example.grabhiring.presenter

import com.example.grabhiring.domain.factory.NewsDomainModelFactory
import com.example.grabhiring.domain.main.NewsUseCase
import com.example.grabhiring.presenter.factory.NewsPresenterEntityFactory
import com.example.grabhiring.presenter.main.MainContract.MainView
import com.example.grabhiring.presenter.main.MainPresenterImpl
import com.example.grabhiring.presenter.mapper.NewsPresenterEntityMapper
import com.example.grabhiring.ui.MainScheduler
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.uber.autodispose.lifecycle.TestLifecycleScopeProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterImplTest {

  @Mock
  private lateinit var newsUseCase: NewsUseCase
  @Mock
  private lateinit var newsPresenterEntityMapper: NewsPresenterEntityMapper
  @Mock
  private lateinit var mainScheduler: MainScheduler
  @Mock
  private lateinit var mainView: MainView
  private lateinit var mainPresenterImpl: MainPresenterImpl
  private lateinit var testScopeProvider: TestLifecycleScopeProvider

  @Before
  fun setup() {
    mainPresenterImpl = MainPresenterImpl(newsUseCase, newsPresenterEntityMapper, mainScheduler)
    mainPresenterImpl.attachView(mainView)
    testScopeProvider = TestLifecycleScopeProvider
      .createInitial(TestLifecycleScopeProvider.TestLifecycle.STARTED)

    `when`(mainScheduler.getMainScheduler()).thenReturn(Schedulers.trampoline())
    `when`(mainView.getScope()).thenReturn(testScopeProvider)
  }

  @Test
  fun `should show error on decorateView call failure`() {
    `when`(newsUseCase.getNews()).thenReturn(Single.error(Exception()))

    mainPresenterImpl.decorateView()

    verify(mainScheduler).getMainScheduler()
    verify(newsUseCase).getNews()
    verify(mainView).getScope()
    verify(mainView).showProgressLoader()
    verify(mainView).hideNewsList()
    verify(mainView).hideProgressLoader()
    verify(mainView).showErrorMessage()
  }

  @Test
  fun `should show news on decorateView call success`() {
    val newsDomainModel = NewsDomainModelFactory.getModel()
    val newsPresenterEntity = NewsPresenterEntityFactory.getModel()
    `when`(newsUseCase.getNews()).thenReturn(Single.just(newsDomainModel))
    `when`(newsPresenterEntityMapper.mapToPresenterEntity(newsDomainModel))
      .thenReturn(newsPresenterEntity)

    mainPresenterImpl.decorateView()

    verify(mainScheduler).getMainScheduler()
    verify(newsPresenterEntityMapper).mapToPresenterEntity(newsDomainModel)
    verify(newsUseCase).getNews()
    verify(mainView).getScope()
    verify(mainView).showProgressLoader()
    verify(mainView).hideNewsList()
    verify(mainView).hideProgressLoader()
    verify(mainView).showNewsList()
    verify(mainView).setNewsList(newsPresenterEntity)
  }


  @Test
  fun `should show error on handleRefreshClick call failure`() {
    `when`(newsUseCase.getNews()).thenReturn(Single.error(Exception()))

    mainPresenterImpl.handleRefreshClick()

    verify(mainScheduler).getMainScheduler()
    verify(newsUseCase).getNews()
    verify(mainView).getScope()
    verify(mainView).showProgressLoader()
    verify(mainView).hideNewsList()
    verify(mainView).hideProgressLoader()
    verify(mainView).showErrorMessage()
  }

  @Test
  fun `should show news on handleRefreshClick call success`() {
    val newsDomainModel = NewsDomainModelFactory.getModel()
    val newsPresenterEntity = NewsPresenterEntityFactory.getModel()
    `when`(newsUseCase.getNews()).thenReturn(Single.just(newsDomainModel))
    `when`(newsPresenterEntityMapper.mapToPresenterEntity(newsDomainModel))
      .thenReturn(newsPresenterEntity)

    mainPresenterImpl.handleRefreshClick()

    verify(mainScheduler).getMainScheduler()
    verify(newsPresenterEntityMapper).mapToPresenterEntity(newsDomainModel)
    verify(newsUseCase).getNews()
    verify(mainView).getScope()
    verify(mainView).showProgressLoader()
    verify(mainView).hideNewsList()
    verify(mainView).hideProgressLoader()
    verify(mainView).showNewsList()
    verify(mainView).setNewsList(newsPresenterEntity)
  }

  @After
  fun tearDown() {
    verifyNoMoreInteractions(newsUseCase, newsPresenterEntityMapper, mainScheduler, mainView)
    mainPresenterImpl.detachView()
  }

}