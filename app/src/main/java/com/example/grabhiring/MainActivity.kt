package com.example.grabhiring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.grabhiring.data.Repository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var repository: Repository

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    repository.getNews()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ println("sudususususus")}, {})
  }
}
