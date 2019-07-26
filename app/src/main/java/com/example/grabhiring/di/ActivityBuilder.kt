package com.example.grabhiring.di

import com.example.decathlonhiring.di.scopes.PerActivity
import com.example.grabhiring.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @PerActivity
  @ContributesAndroidInjector
  abstract fun mainActivityInjector(): MainActivity

}
