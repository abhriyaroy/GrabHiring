package com.example.grabhiring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.grabhiring.data.api.NewsApiClient
import com.example.grabhiring.data.database.DatabaseHelper
import dagger.android.AndroidInjection
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var databaseHelper: DatabaseHelper
  @Inject
  lateinit var newsApiClient: NewsApiClient

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    newsApiClient.getNews()
      .flatMap {
        databaseHelper.getDatabase()
          .newsDao()
          .saveCache(it)
        Single.just("asdf")
      }.flatMap {
        databaseHelper.getDatabase().newsDao()
          .getCache()
      }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
          textView.text = it[0].status
      }, {
      println(it.message)
      })
  }
}
