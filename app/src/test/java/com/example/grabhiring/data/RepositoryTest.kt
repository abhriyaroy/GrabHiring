package com.example.grabhiring.data

import com.example.grabhiring.data.api.NewsApiClientFactory
import com.example.grabhiring.data.database.DatabaseHelper
import com.example.grabhiring.data.factory.NewsDataEntityFactory
import com.example.grabhiring.data.mapper.NewsDataEntityMapper
import com.example.grabhiring.domain.factory.NewsDomainModelFactory
import com.example.grabhiring.exceptions.CacheAbsentException
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

  @Mock
  private lateinit var newsApiClientFactory: NewsApiClientFactory
  @Mock
  private lateinit var databaseHelper: DatabaseHelper
  @Mock
  private lateinit var mapper: NewsDataEntityMapper
  @Mock
  private lateinit var backgroundScheduler: BackgroundScheduler
  private lateinit var repositoryImpl: RepositoryImpl
  private lateinit var testObserver: TestObserver<Any>

  @Before
  fun setup() {
    repositoryImpl =
      RepositoryImpl(newsApiClientFactory, databaseHelper, mapper, backgroundScheduler)
    testObserver = TestObserver()

    `when`(backgroundScheduler.getIoScheduler()).thenReturn(Schedulers.trampoline())
  }

  @Test
  fun `should return error on getNews call failure due to api call error`() {
    `when`(newsApiClientFactory.getNews()).thenReturn(Single.error(Exception()))
    `when`(databaseHelper.getCachedNews()).thenReturn(Single.error(Exception("cache")))

    repositoryImpl.getNews().subscribe(testObserver)

    testObserver.assertError(Exception::class.java)
    testObserver.assertErrorMessage("cache")
    verify(backgroundScheduler).getIoScheduler()
    verify(newsApiClientFactory).getNews()
    verify(databaseHelper).getCachedNews()
  }

  @Test
  fun `should return error on getNews call failure due to save to cache error`() {
    val newsDataEntity = NewsDataEntityFactory.getModel()
    `when`(newsApiClientFactory.getNews()).thenReturn(Single.just(newsDataEntity))
    `when`(databaseHelper.saveToCache(newsDataEntity)).thenReturn(Single.error(Exception()))
    `when`(databaseHelper.getCachedNews()).thenReturn(Single.error(Exception("cache")))

    repositoryImpl.getNews().subscribe(testObserver)

    testObserver.assertError(Exception::class.java)
    testObserver.assertErrorMessage("cache")
    verify(backgroundScheduler).getIoScheduler()
    verify(newsApiClientFactory).getNews()
    verify(databaseHelper).clearCache()
    verify(databaseHelper).saveToCache(newsDataEntity)
    verify(databaseHelper).getCachedNews()
  }

  @Test
  fun `should return cache absent error on getNews call failure due to cache not being present`() {
    val newsDataEntity = NewsDataEntityFactory.getModel()
    `when`(newsApiClientFactory.getNews()).thenReturn(Single.just(newsDataEntity))
    `when`(databaseHelper.saveToCache(newsDataEntity)).thenReturn(Single.just(Random.nextLong()))
    `when`(databaseHelper.getCachedNews()).thenReturn(Single.error(CacheAbsentException()))

    repositoryImpl.getNews().subscribe(testObserver)

    testObserver.assertError(CacheAbsentException::class.java)
    verify(backgroundScheduler).getIoScheduler()
    verify(newsApiClientFactory).getNews()
    verify(databaseHelper).clearCache()
    verify(databaseHelper).saveToCache(newsDataEntity)
    verify(databaseHelper).getCachedNews()
  }

  @Test
  fun `should return news domain model on getNews call success`() {
    val newsDataEntity = NewsDataEntityFactory.getModel()
    val newsDomainModel = NewsDomainModelFactory.getModel()
    `when`(newsApiClientFactory.getNews()).thenReturn(Single.just(newsDataEntity))
    `when`(databaseHelper.saveToCache(newsDataEntity)).thenReturn(Single.just(Random.nextLong()))
    `when`(databaseHelper.getCachedNews()).thenReturn(Single.just(newsDataEntity))
    `when`(mapper.mapFromDataEntity(newsDataEntity)).thenReturn(newsDomainModel)

    val result = repositoryImpl.getNews().test().values()[0]

    assertEquals(newsDomainModel, result)
    verify(mapper).mapFromDataEntity(newsDataEntity)
    verify(backgroundScheduler).getIoScheduler()
    verify(newsApiClientFactory).getNews()
    verify(databaseHelper).clearCache()
    verify(databaseHelper).saveToCache(newsDataEntity)
    verify(databaseHelper).getCachedNews()
  }

  @After
  fun tearDown() {
    verifyNoMoreInteractions(newsApiClientFactory, databaseHelper, mapper, backgroundScheduler)
  }

}