package com.example.grabhiring.ui.details

import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.presenter.details.DetailsContract.DetailsPresenter
import com.example.grabhiring.presenter.details.DetailsPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

  @PerActivity
  @Provides
  fun providesDetailsPresenter(): DetailsPresenter = DetailsPresenterImpl()
}