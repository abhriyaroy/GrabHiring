package com.example.grabhiring.di

import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.ui.main.MainActivity
import com.example.grabhiring.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @PerActivity
  @ContributesAndroidInjector(modules = [(MainModule::class)])
  abstract fun contributesMainActivityInjector(): MainActivity

}
