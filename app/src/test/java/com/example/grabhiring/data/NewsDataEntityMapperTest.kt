package com.example.grabhiring.data

import com.example.grabhiring.data.mapper.NewsDataEntityMapper
import com.example.grabhiring.data.mapper.NewsDataEntityMapperImpl
import com.example.grabhiring.data.model.ArticlesDataEntity
import com.example.grabhiring.data.model.NewsDataEntity
import com.example.grabhiring.data.model.SourceDataEntity
import com.example.grabhiring.domain.model.ArticlesDomainModel
import com.example.grabhiring.domain.model.NewsDomainModel
import com.example.grabhiring.domain.model.SourceDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.UUID.randomUUID

@RunWith(MockitoJUnitRunner::class)
class NewsDataEntityMapperTest {

  private lateinit var newsDataEntityMapper: NewsDataEntityMapper
  private lateinit var newsDataEntity: NewsDataEntity
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
    newsDataEntityMapper = NewsDataEntityMapperImpl()
    newsDataEntity = NewsDataEntity(
      status, totalResults,
      arrayListOf(
        ArticlesDataEntity(
          SourceDataEntity(sourceId, sourceName),
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
  fun `should return news domain model on mapFromDataEntity call success`() {
    val result = newsDataEntityMapper.mapFromDataEntity(newsDataEntity)
    assertEquals(newsDomainModel, result)
  }

  @Test
  fun `should return data entity model on mapToDataEntity call success`() {
    val result = newsDataEntityMapper.mapToDataEntity(newsDomainModel)
    assertEquals(newsDataEntity, result)
  }

  @Before
  fun after() {

  }

}