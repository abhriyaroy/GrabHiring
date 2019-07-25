package com.example.grabhiring.di

import android.app.Application
import android.content.Context
import com.example.decathlonhiring.di.scopes.PerApplication
import com.example.grabhiring.data.BackgroundScheduler
import com.example.grabhiring.data.BackgroundSchedulerImpl
import com.example.grabhiring.data.Repository
import com.example.grabhiring.data.RepositoryImpl
import com.example.grabhiring.data.api.NewsApiClientFactory
import com.example.grabhiring.data.api.NewsApiClientFactoryImpl
import com.example.grabhiring.data.database.DatabaseHelper
import com.example.grabhiring.data.database.DatabaseHelperImpl
import com.example.grabhiring.data.mapper.NewsDataEntityMapper
import com.example.grabhiring.data.mapper.NewsDataEntityMapperImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

  @PerApplication
  @Provides
  fun providesContext(application: Application): Context = application

  @PerApplication
  @Provides
  fun providesNewsDataEntityMapper(): NewsDataEntityMapper = NewsDataEntityMapperImpl()

  @PerApplication
  @Provides
  fun providesNewsApiClientFactory(): NewsApiClientFactory = NewsApiClientFactoryImpl()

  @PerApplication
  @Provides
  fun providesBackgroundScheduler(): BackgroundScheduler = BackgroundSchedulerImpl()

  @PerApplication
  @Provides
  fun providesDatabaseHelper(): DatabaseHelper = DatabaseHelperImpl()

  @PerApplication
  @Provides
  fun providesRepository(
    apiClientFactory: NewsApiClientFactory,
    databaseHelper: DatabaseHelper,
    newsDataEntityMapper: NewsDataEntityMapper,
    backgroundScheduler: BackgroundScheduler
  ): Repository =
    RepositoryImpl(apiClientFactory, , databaseHelper, newsDataEntityMapper, backgroundScheduler)
}