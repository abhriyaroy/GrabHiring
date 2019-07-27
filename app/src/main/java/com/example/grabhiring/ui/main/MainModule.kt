package com.example.grabhiring.ui.main

import android.content.Context
import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.domain.main.NewsUseCase
import com.example.grabhiring.presenter.main.MainContract.MainPresenter
import com.example.grabhiring.presenter.main.MainPresenterImpl
import com.example.grabhiring.presenter.mapper.NewsPresenterEntityMapper
import com.example.grabhiring.presenter.mapper.NewsPresenterEntityMapperImpl
import com.example.grabhiring.ui.MainScheduler
import com.example.grabhiring.ui.imageloader.ImageLoader
import com.example.grabhiring.ui.imageloader.ImageLoaderImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

  @PerActivity
  @Provides
  fun providesMainPresenter(
    newsUseCase: NewsUseCase,
    newsPresenterEntityMapper: NewsPresenterEntityMapper,
    mainScheduler: MainScheduler
  ): MainPresenter =
    MainPresenterImpl(newsUseCase, newsPresenterEntityMapper, mainScheduler)

  @PerActivity
  @Provides
  fun providesNewsPresenterEntityMapper(): NewsPresenterEntityMapper =
    NewsPresenterEntityMapperImpl()

  @PerActivity
  @Provides
  fun providesImageLoader(context: Context): ImageLoader = ImageLoaderImpl(context)
}