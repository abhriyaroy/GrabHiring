package com.example.grabhiring

import android.app.Activity
import android.app.Application
import com.example.grabhiring.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class GrabHiringApplication : Application(), HasActivityInjector {

  @Inject
  internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    DaggerAppComponent.builder()
      .application(this)
      .build()
      .inject(this)
  }

  override fun activityInjector() = activityDispatchingAndroidInjector

}