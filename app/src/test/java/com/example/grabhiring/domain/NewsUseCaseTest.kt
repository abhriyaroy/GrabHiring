package com.example.grabhiring.domain

import com.example.grabhiring.data.Repository
import com.example.grabhiring.domain.factory.NewsDomainModelFactory
import com.example.grabhiring.domain.main.NewsInteractor
import com.example.grabhiring.domain.main.NewsUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsUseCaseTest {

  @Mock
  private lateinit var repository: Repository
  private lateinit var newsUseCase: NewsUseCase

  @Before
  fun setup() {
    newsUseCase = NewsInteractor(repository)
  }

  @Test
  fun `should return single of news domain model on getNews call success`() {
    val newsDomainModel = NewsDomainModelFactory.getModel()
    `when`(repository.getNews()).thenReturn(Single.just(newsDomainModel))

    val result = newsUseCase.getNews().test().values()[0]

    assertEquals(newsDomainModel, result)
    verify(repository).getNews()
  }

  @After
  fun tearDown() {
    verifyNoMoreInteractions(repository)
  }

}