package com.example.grabhiring.di

import android.app.Application
import com.example.decathlonhiring.di.scopes.PerApplication
import com.example.grabhiring.ui.GrabHiringApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApplication
@Component(
  modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)]
)
interface AppComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: GrabHiringApplication)

}