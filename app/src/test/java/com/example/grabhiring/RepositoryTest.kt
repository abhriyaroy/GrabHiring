package com.example.grabhiring

import com.example.grabhiring.data.BackgroundScheduler
import com.example.grabhiring.data.RepositoryImpl
import com.example.grabhiring.data.api.NewsApiClientFactory
import com.example.grabhiring.data.database.DatabaseHelper
import com.example.grabhiring.data.mapper.NewsDataEntityMapper
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

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

  @Before
  fun setup() {
    repositoryImpl =
      RepositoryImpl(newsApiClientFactory, databaseHelper, mapper, backgroundScheduler)
  }

  @Test fun

  @After
  fun tearDown() {
    verifyNoMoreInteractions(newsApiClientFactory, databaseHelper, mapper, backgroundScheduler)
  }

}