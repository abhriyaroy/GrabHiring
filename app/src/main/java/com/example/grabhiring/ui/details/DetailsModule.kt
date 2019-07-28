package com.example.grabhiring.ui.details

import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.presenter.details.DetailsContract.DetailsPresenter
import com.example.grabhiring.presenter.details.DetailsPresenterImpl
import com.example.grabhiring.ui.utils.ResourceUtils
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

  @PerActivity
  @Provides
  fun providesDetailsPresenter(resourceUtils: ResourceUtils)
      : DetailsPresenter = DetailsPresenterImpl(resourceUtils)
}