package com.example.grabhiring.di

import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.ui.adapter.NewsListAdapterModule
import com.example.grabhiring.ui.details.DetailsActivity
import com.example.grabhiring.ui.details.DetailsModule
import com.example.grabhiring.ui.main.MainActivity
import com.example.grabhiring.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @PerActivity
  @ContributesAndroidInjector(modules = [(MainModule::class), (NewsListAdapterModule::class)])
  abstract fun contributesMainActivityInjector(): MainActivity

  @PerActivity
  @ContributesAndroidInjector(modules = [(DetailsModule::class)])
  abstract fun contributesDetailActivityInejector(): DetailsActivity

}
