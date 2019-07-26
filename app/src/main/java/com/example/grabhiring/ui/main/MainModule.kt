package com.example.grabhiring.ui.main

import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.domain.main.NewsUseCase
import com.example.grabhiring.presenter.main.MainContract.MainPresenter
import com.example.grabhiring.presenter.main.MainPresenterImpl
import com.example.grabhiring.ui.MainScheduler
import dagger.Module
import dagger.Provides

@Module
class MainModule {

  @PerActivity
  @Provides
  fun providesMainPresenter(newsUseCase: NewsUseCase, mainScheduler: MainScheduler): MainPresenter =
    MainPresenterImpl(newsUseCase, mainScheduler)
}