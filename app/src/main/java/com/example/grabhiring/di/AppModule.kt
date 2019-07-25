package com.example.grabhiring.di

import android.app.Application
import android.content.Context
import com.example.decathlonhiring.di.scopes.PerApplication
import com.example.grabhiring.data.Repository
import com.example.grabhiring.data.RepositoryImpl
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
  fun providesRepository(newsDataEntityMapper: NewsDataEntityMapper): Repository =
    RepositoryImpl(newsDataEntityMapper)
}