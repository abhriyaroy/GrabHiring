package com.example.grabhiring.presenter

import com.example.grabhiring.domain.model.ArticlesDomainModel
import com.example.grabhiring.domain.model.NewsDomainModel
import com.example.grabhiring.domain.model.SourceDomainModel
import com.example.grabhiring.presenter.mapper.NewsPresenterEntityMapper
import com.example.grabhiring.presenter.mapper.NewsPresenterEntityMapperImpl
import com.example.grabhiring.presenter.model.ArticlesPresenterEntity
import com.example.grabhiring.presenter.model.NewsPresenterEntity
import com.example.grabhiring.presenter.model.SourcePresenterEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.UUID.randomUUID

@RunWith(MockitoJUnitRunner::class)
class NewsPresenterEntityMapperTest {

  private lateinit var newsPresenterEntityMapper: NewsPresenterEntityMapper
  private lateinit var newsPresenterEntity: NewsPresenterEntity
  private lateinit var newsDomainModel: NewsDomainModel
  private val status = randomUUID().toString()
  private val totalResults = Random().nextInt()
  private val sourceId = randomUUID().toString()
  private val sourceName = randomUUID().toString()
  private val author = randomUUID().toString()
  private val title = randomUUID().toString()
  private val description = randomUUID().toString()
  private val url = randomUUID().toString()
  private val urlToImage = randomUUID().toString()
  private val publishedAt = randomUUID().toString()
  private val content = randomUUID().toString()

  @Before
  fun setup() {
    newsPresenterEntityMapper = NewsPresenterEntityMapperImpl()
    newsPresenterEntity = NewsPresenterEntity(
      status, totalResults,
      arrayListOf(
        ArticlesPresenterEntity(
          SourcePresenterEntity(sourceId, sourceName),
          author, title, description, url, urlToImage, publishedAt, content
        )
      )
    )
    newsDomainModel = NewsDomainModel(
      status, totalResults,
      arrayListOf(
        ArticlesDomainModel(
          SourceDomainModel(sourceId, sourceName),
          author, title, description, url, urlToImage, publishedAt, content
        )
      )
    )
  }

  @Test
  fun `should return presenter entity model on mapToPresenterEntity call success`() {
    val result = newsPresenterEntityMapper.mapToPresenterEntity(newsDomainModel)
    assertEquals(newsPresenterEntity, result)
  }

}